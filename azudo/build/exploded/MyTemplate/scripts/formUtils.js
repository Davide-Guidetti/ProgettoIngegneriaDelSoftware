/*
linit the number of characters in a text field
you may want to use maxlength="MaxNum" html attribute instead
*/
function maxNChars(NMaxChars, inputElement, elemToShowRemainig){
	var content = inputElement.value;
	if(content.length > NMaxChars)
		inputElement.value = content.slice(0, NMaxChars);
	if(true)
		elemToShowRemainig.innerHTML = NMaxChars - inputElement.value.length;
}

/*
Check for "valid" email: not empty, has "@" sign and "."
parameter:	String containig the email to check
return:		true if parameter is correct, false otherwise.
*/
function isEmailValid(myField) {
	var result = false;
	if ( myField.length > 0 ) {
		var tempstr = myField;
		var aindex = tempstr.indexOf("@");
		if (aindex > 0 ) {
			var pindex = tempstr.indexOf(".",aindex);
		        if ( (pindex > aindex+1) && (tempstr.length > pindex+1) ) {
				result = true;
			} else {
				result = false;
			}
		}
	}
	if (!result) {
		alert("Please enter a valid email address in the form: yourname@yourdomain.com");
	}
	return result;
}

/*
Very similar to JQuery serializeArray():
Converts all of the form fields into a javascript object having as property name 
the content of the "name" attribute (which must be present, otherwise it wont be considered)  of the <input> (or <select> or <textarea>) element
parameter: a valid JQuery DOM form element
return: the newly created object
*/
function formToObject(JQueryHTMLForm){
	var formVals = new Object();
	JQueryHTMLForm.children("[name]").each(function(){
		var inputEl = $(this);
		var type = inputEl.attr("type");
		var name = inputEl.attr("name");
		if(inputEl.is("input") && type != undefined && name != undefined){
			if(type == "text" || type == "password" || type == "file" || type == "select"){
				formVals[name] = inputEl.val();
			}else if(type == "checkbox"){
				formVals[name] = inputEl.is(":checked");
			}else if(type == "radio"){
				if(inputEl.is(":checked")){
					formVals[name] = inputEl.val();
				}
			}
		}
		else if(inputEl.is("select") &&  name != undefined){
			formVals[name] = inputEl.val();
		}else if(inputEl.is("textArea") &&  name != undefined){
			formVals[name] = inputEl.val();
		}
	});
	return formVals;
}