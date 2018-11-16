var slider = document.getElementById("myRange");
var output = document.getElementById("slideRange");
output.innerHTML = slider.value;

slider.oninput = function() {
	output.innerHTML = this.value;
}

var submitNode = document.getElementById("finalSubmit");

submitNode.addEventListener("click", validatePrice, false);

function validatePrice(event) {
	var lowPrice = document.getElementById("lowPrice").value;
	var highPrice = document.getElementById("highPrice").value;
	var priceError = document.getElementById("priceError");
	var errMsg;
	var faultFound = false;

	if (!lowPrice) {
		lowPrice = 0;
	}
	if (!highPrice) {
		highPrice = 999999999;
	}
	if (isNaN(lowPrice) && isNaN(highPrice)) {
		
		faultFound = true;
		errMsg = "Min price and max price are not a number!";
	} else if (isNaN(highPrice)) {
		faultFound = true;
		errMsg = "Max price is not a number!";
	} else if (isNaN(lowPrice)) {
		faultFound = true;
		errMsg = "Min price is not a number!";
	} else{
		lowPrice = parseInt(lowPrice);
		highPrice = parseInt(highPrice);
		
		if (lowPrice > highPrice) {
			faultFound = true;
			errMsg = "Min price is equal or more than max price! " ;
		}
	}

	
	if (faultFound) {
		event.preventDefault();
		priceError.style.display = "block"
		priceError.innerHTML = errMsg;
		priceError.focus();
		priceError.select();
		return false;
	} else {
		return true;
	}

}