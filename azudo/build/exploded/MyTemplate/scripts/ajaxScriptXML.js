/*
 * Funzione per estrarre il contenuto CDATA presente
 * all'interno di un nodo XML 
 * [ad esempio su <guida>ajax</guida> restituisce solo ajax].
 *
 * Utile a far rimanere leggibile il codice della funzione di callback che fa parsing 
 */
function leggiContenuto(item, nomeNodo) {
	
	return item.getElementsByTagName(nomeNodo)[0].firstChild.nodeValue;
};

/*
 * Funzione di callback: gestisce arrivo della risposta
 */
function callback(theXhr, theElement, parseCallbac) {
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
			if (theXhr.responseXML) {
				//essendo una risposta di tipo XML, ho bisogno di una funzione che me lo traduca in html
				HTMLString = parseCallbac(theXhr.responseXML, theElement); 
				if(HTMLString !== null)
					theElement.innerHTML = HTMLString;
			}
			else{
				// visualizzazione contenuto letto evitando di scrivere la risposta in modo interpretabile dal browser
				theElement.innerHTML = "L'XML restituito dalla richiesta non è valido.<br />" + theXhr.responseText.split('<').join("&lt;").split('>').join("&gt;");
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
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theElement del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function funzioneAJAX(theUri, theElement, parseCallbac, theXhr) {

	//impostimao il timeout
	theXhr.timeout = 1500; // time in milliseconds
	theXhr.ontimeout = function (e) {
  		theElement.innerHTML = "request timed out";
	};

	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement, parseCallbac); };

	// impostazione richiesta asincrona in GET del file specificato
	try {
		theXhr.open("get", theUri, true);
	}
	catch (e) {
		// NOTE: Exceptions are raised when trying to access cross-domain URIs
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);
}


/*
 * Imposta il contenuto disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX; per browser legacy
 */
function funzioneIframe(theUri, theElement) {
	// qui faccio scaricare al browser direttamente il contenuto dell'XML come src dell'iframe.
	theElement.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta ne AJAX, ne gli iframe. Forse è il caso di aggiornarlo!</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! e' il browser che renderizza l'src dell'iframe!
}


/*
 * Scarica un contenuto xml dall'uri fornito, chiama la funzione di callback per il parsing,
 * e lo aggiunge al contenuto dell'elemento (sempre che la funzione di callback non restituisca null, 
 * in tal caso non viene fatta alcuna modifica)
 * Gestisce come possibile casi in cui la richiesta non arrivi, ajax non supportato, ecc...
 */
function funzioneXML(uri, element, parseCallbac) {
	var xhr = new XMLHttpRequest();
	if (xhr)
		funzioneAJAX(uri, element, parseCallbac, xhr);
	else
		funzioneIframe(uri, element); //AJAX-XML non è supportato! useremo un iframe
}
