function validate() {
	var pos = document.IntroQuest.Position;
	if(positionSelect(pos)) {
	}
	return false;
}

function positionSelect(pos) {
	if(pos.value == "-Select") {
		alert("Please select a position");
		pos.focus();
		return false;
	}
	else
		return true;
}
