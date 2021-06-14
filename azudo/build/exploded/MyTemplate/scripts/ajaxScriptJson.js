/*
 * Funzione di callback: gestisce arrivo della risposta
 */
function callbackPerJson(theXhr, theElement, parseCallbac) {
	// verifica dello stato
	if (theXhr.readyState === 2) {
		theElement.innerHTML = "Richiesta inviata...";
	}
	else if (theXhr.readyState === 3) {
		theElement.innerHTML = "Ricezione della risposta...";
	}
	else if (theXhr.readyState === 4) { //when abort() is called state changes to 4 with an error
		// verifica della risposta da parte del server
		if (theXhr.status === 200) { // operazione avvenuta con successo
			
			//essendo una risposta con un generico oggetto, ho bisogno di una funzione che me lo traduca in html
			try {
				HTMLString = parseCallbac(JSON.parse(theXhr.responseText), theElement); 
				if(HTMLString !== null)
					theElement.innerHTML = HTMLString;
			} catch (e) {
				// visualizzazione contenuto letto evitando di scrivere la risposta in modo interpretabile dal browser
				theElement.innerHTML = "L'Ajax restituito dalla richiesta non è valido.<br />" + theXhr.responseText.split('<').join("&lt;").split('>').join("&gt;");
			}
		}
		else {
			// errore di caricamento
			theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			theElement.innerHTML += "Errore riscontrato: " + theXhr.status;
		}
	}
}



/*
 * Imposta il contenuto disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX; per browser legacy
 */
function funzioneIframeJson(theUri, parameters, theHolder, JSONtoSend) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	if(JSONtoSend==null)
		theHolder.innerHTML = '<iframe src="' + theUri+"?"+parameters + '" width="50%" height="50px">Il tuo browser non supporta ne AJAX, ne gli iframe. Forse è il caso di aggiornarlo!</iframe>';
	else
		theHolder.innerHTML = '<iframe src="' + theUri+"?"+parameters+JSONtoSend + '" width="50%" height="50px">Il tuo browser non supporta ne AJAX, ne gli iframe. Forse è il caso di aggiornarlo!</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! e' il browser che renderizza l'src dell'iframe!
}


function funzioneAJAXPerJson(theUri, parameters, theElement, parseCallbac, theXhr, getOrPost, JSONtoSend) {

	//impostimao il timeout
	theXhr.timeout = 1500; // time in milliseconds
	theXhr.ontimeout = function (e) {
  		theElement.innerHTML = "request timed out";
	};

	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callbackPerJson(theXhr, theElement, parseCallbac); };

	// impostazione richiesta asincrona in GET del file specificato
	try {
		if(getOrPost=="get"){
			if(JSONtoSend!==null)
				theXhr.open("get", theUri+"?"+parameters+btoa(JSONtoSend), true);
			else
				theXhr.open("get", theUri+"?"+parameters, true);
		}else{
			theXhr.open("post", theUri, true);
		}
	}
	catch (e) {
		// N.B: Exceptions are raised when trying to access cross-domain URIs
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	if(getOrPost=="post"){
		theXhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
		if(JSONtoSend!==null){
			theXhr.send(parameters+JSONtoSend);
		}else{
			theXhr.send(parameters);
		}
	}else{
		theXhr.send(null);
	}
}


/*
 * Scarica un contenuto JSON dall'uri fornito, chiama la funzione di callback per il parsing,
 * e lo aggiunge al contenuto dell'elemento (sempre che la funzione di callback non restituisca null, 
 * in tal caso non viene fatta alcuna modifica)
 * Gestisce come possibile casi in cui la richiesta non arrivi, non sia formato JSON, ecc...
 */
//normale richiesta GET senza passaggio di json. Si possono specificare parametri url nell'apposito campo senza problemi
function funzioneJSON(uri, getParameters, element, parseCallbac) {
	var xhr = new XMLHttpRequest();
	if (xhr)
		funzioneAJAXPerJson(uri, getParameters, element, parseCallbac, xhr, "get", null);
	else
		funzioneIframePerJson(uri, getParameters, element, null); //AJAX-XML non è supportato! useremo un iframe
}

//richiesta GET con passaggio di json. Attenzione: i parametri devono terminare con "?NomeParametroConJson=". Aggiungere prima eventuali altri parametri
function funzioneJSON(uri, getParameters, element, parseCallbac, ObjToSend) {
	if(typeof ObjToSend === "undefined")
		ObjToSend = null;
	else
		ObjToSend = JSON.stringify(ObjToSend);
		
	var xhr = new XMLHttpRequest();
	if (xhr)
		funzioneAJAXPerJson(uri, getParameters, element, parseCallbac, xhr, "get", ObjToSend);
	else
		funzioneIframePerJson(uri, getParameters, element, ObjToSend); //AJAX-XML non è supportato! useremo un iframe
}

//normale POST senza passaggio di json
function funzionePostJSON(uri, postParameters, element, parseCallbac) {
	var xhr = new XMLHttpRequest();
	if (xhr)
		funzioneAJAXPerJson(uri, postParameters, element, parseCallbac, xhr, "post", null);
	else
		funzioneIframePerJson(uri, postParameters, element); //AJAX-XML non è supportato! useremo un iframe
}

//richiesta POST con passaggio di json. Attenzione: i parametri devono terminare con "?NomeParametroConJson=". Aggiungere prima eventuali altri parametri
function funzionePostJSON(uri, postParameters, element, parseCallbac, ObjToSend) {
	if(typeof ObjToSend === "undefined")
		ObjToSend = null;
	else
		ObjToSend = JSON.stringify(ObjToSend);
		
	var xhr = new XMLHttpRequest();
	if (xhr)
		funzioneAJAXPerJson(uri, postParameters, element, parseCallbac, xhr, "post", ObjToSend);
	else
		funzioneIframePerJson(uri, postParameters, element, ObjToSend); //AJAX-XML non è supportato! useremo un iframe
}
