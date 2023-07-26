/**
 * 
 */
var startX = '';
var startY = '';

var finalStartX = '';
var finalStartY = '';
var svgLine = '';
let svgCount = 0;




// Create a new Map
var joinPathMap = new Map();
var isFromViewWork = false;

const pathMapList = new Array();
const divMapList = new Array();

function allowDrop(event) {
	event.preventDefault();
}
let startDrag = true;



function dragged(event) {
	if (startDrag) {
		event.dataTransfer.setData("text", event.target.id);
	} else {
		event.preventDefault();
	}
}


function drop(event) {
	event.preventDefault();
	var data = event.dataTransfer.getData("text");
	var button = document.getElementById(data);
	var dropX = button.style.left = (event.pageX - 20) + "px";
	var dropY = button.style.top = (event.pageY - 20) + "px";

	// Get all the SVG elements in the HTML document
	const svgElements = document.getElementsByTagName('svg');
	const numberOfSVGElements = svgElements.length;
	console.log("Number of SVG line exists on the body......" + numberOfSVGElements);

	getStartXnY();


	//var svgLine = parent.document.getElementById('svg' + (svgCount));	
	var svgLine = svgg;
	if (svgLine && svgLine.parentNode === document.body) {

		var handleR = document.getElementById("handleR");
		var handleRX = event.clientX - 8;
		var handleRY = event.clientY + 28;
		var pathLinee = parent.document.getElementById('path' + (svgCount));
		console.log("Path Linee = parent.document.getElementById('path'+(svgCount-1)) ===> " + pathLinee);

		var pathId = "";
		var markerLine = parent.document.getElementById('merkerr' + (svgCount));
		var arrowPathLine = parent.document.getElementById('arrowPath' + (svgCount));


		const pathKeys = joinPathMap.keys();
		let pathKey = pathKeys.next();
		while (!pathKey.done) {
			console.log("Path Key ===> " + pathKey);
			console.log("Path Key.Value ===> " + pathKey.value);
			const handleMap = joinPathMap.get(pathKey.value);
			const handleLLs = handleMap.keys();
			let handleLL = handleLLs.next().value;
			//console.log("handleLL - " + handleLL  );
			const handleRR = handleMap.get(handleLL);
			console.log(pathKey.value + "<====> " + handleLL + ", " + handleRR);



			const roundL = document.getElementById(handleLL);
			const roundR = document.getElementById(handleRR);

			const buttonPositionL = roundL.getBoundingClientRect();
			const buttonPositionR = roundR.getBoundingClientRect();
			pathId = pathKey.value;
			console.log("Path Id = pathKey.value ===> " + pathKey.value);
			const pathLine = parent.document.getElementById(pathId);
			const iPathId = "ipath" + pathId.slice(-1);
			console.log("iPathId == " + iPathId);
			const iPathLine = parent.document.getElementById(iPathId);
			console.log("Path Line = parent.document.getElementById(ipathId) ===> " + pathLine);
			//console.log("pathLine.tagName ===> " + pathLine.tagName);

			console.log("buttonPositionL.top " + buttonPositionL.top + " window.scrollY " + window.scrollY);
			let x = (buttonPositionL.left + window.scrollX);
			let y = (buttonPositionL.top + window.scrollY);
			console.log("haldleLX " + x + " haldleLY " + y);

			const haldleLX = x;
			const haldleLY = y;

			console.log("buttonPositionR.top " + buttonPositionR.top + " window.scrollY " + window.scrollY);
			let a = (buttonPositionR.left + window.scrollX);
			let b = (buttonPositionR.top + window.scrollY);
			console.log("handleRX " + a + " handleRY " + b);

			const handleRX = a;
			const handleRY = b;

			console.log("<////> svgLine: " + svgLine + " markerLine: " + markerLine + " pathLine: " + pathLine + " arrowPathLine: " + arrowPathLine + " haldleLX: " + haldleLX + " haldleLY: " + haldleLY + " handleRX: " + handleRX + " handleRY: " + handleRY);


			//console.log(`Button ID: ${buttonId}`);
			//console.log(`Button position: ${buttonPosition.top}, ${buttonPosition.left}`);
			const midRectId = "midRect" + pathId.slice(-1);
			console.log("midRectId >>>>>> " + midRectId);
			const midRectLine = parent.document.getElementById(midRectId);
			reDrawArrow(svgLine, markerLine, pathLine, iPathLine, midRectId, arrowPathLine, haldleLX, haldleLY, handleRX, handleRY);
			pathKey = pathKeys.next();
		}



		//reDrawArrow(svgLine, markerLine, pathLine, arrowPathLine, startX, startY, handleRX,handleRY);

		console.log("SVG line exists on the body......");
	} else {
		// The SVG line does not exist on the body
		console.log("SVG line does not exist on the body......");
	}

}
//const hiddenDivs = parentBoxElement.querySelectorAll('div[style*="display:none"]');
// Function to toggle the visibility of a div element

function toggleVisibilityForView(elementId, isFromViewWork) {
	console.log("toggleVisibilityForView id :: " + elementId);
	var isDisplayed = false;
	if (elementId != 'masterDiv') {
		console.log("not master");
		var element = document.getElementById(elementId);
		console.log("In toggleVisibility Element Name: " + element + "elementId:  " + elementId);
		//var isDisplayed = false;
		//const allDivs = element.querySelectorAll("DIV");
		//allDivs.forEach((div) => {
		if (element.style.display === "none" && isFromViewWork) {
			element.style.display = "block";
			moveBoxes(elementId, isDisplayed);
		}
		//});
	}
};

function toggleVisibility(elementId) {
	var element = document.getElementById(elementId);
	console.log("In toggleVisibility Element Name: " + element + "elementId:  " + elementId);
	var isDisplayed = false;

	if (element.style.display === "none") {
		element.style.display = "block";
		//element.previousSibling.classList.add("active");

		moveBoxes(elementId, isDisplayed);

		const buttons = element.querySelectorAll("button");
		let counter = 0;
		buttons.forEach((button) => {
			var buttonId = button.id;
			console.log("HIDDEN BUTTONS >> " + button.id + "buttons.length " + buttons.length);

			const pathKeys = joinPathMap.keys();
			const pathKeysforLog = Array.from(joinPathMap.keys());

			console.log("pathKeys.length == 	 " + pathKeysforLog.length);
			let pathKey = pathKeys.next();
			while (!pathKey.done) {
				//console.log("Path Key 1 ===> " + pathKey);
				console.log("Path Key.Value 2===> " + pathKey.value);
				var pathId = pathKey.value
				const handleMap = joinPathMap.get(pathKey.value);

				if (handleMap.has(buttonId)) {
					const element = parent.document.getElementById(pathId);
					if (element != null)
						element.style.display = "block";
					console.log("Remove callled");
				}
				if (([...handleMap.values()].includes(buttonId))) {
					const element = parent.document.getElementById(pathId);
					if (element != null)
						element.style.display = "block";
					console.log("Remove callled..");
				}

				pathKey = pathKeys.next();
				console.log("Path Key.Value ===> " + pathKey.value);

				console.log("Iteration #" + counter);
				counter++;
				//break;
				//if(counter === 3)
				//{ break; }
			}

		});


	} else {
		element.style.display = "none";
		//element.previousSibling.classList.remove("active");

		moveBoxes(elementId, true);


		//const hiddenContainerDiv = document.getElementById("hiddenContainerDiv2");

		const buttons = element.querySelectorAll("button");
		let counter = 0;
		buttons.forEach((button) => {
			var buttonId = button.id;
			console.log("HIDDEN BUTTONS >> " + button.id + "buttons.length " + buttons.length);

			const pathKeys = joinPathMap.keys();
			const pathKeysforLog = Array.from(joinPathMap.keys());

			console.log("pathKeys.length == 	 " + pathKeysforLog.length);
			let pathKey = pathKeys.next();
			while (!pathKey.done) {
				//console.log("Path Key 1 ===> " + pathKey);
				console.log("Path Key.Value 2===> " + pathKey.value);
				var pathId = pathKey.value
				const handleMap = joinPathMap.get(pathKey.value);

				if (handleMap.has(buttonId)) {
					const element = parent.document.getElementById(pathId);
					if (element != null)
						element.style.display = "none";
					console.log("Removed callled");
				}
				if (([...handleMap.values()].includes(buttonId))) {
					const element = parent.document.getElementById(pathId);
					if (element != null)
						element.style.display = "none";
					console.log("Removed callled..");
				}

				pathKey = pathKeys.next();
				console.log("Path Key.Value ===> " + pathKey.value);

				console.log("Iteration #" + counter);
				counter++;

			}

		});


	}
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

// Event listener for when the mouse is pressed down on the first button
document.addEventListener("mousedown", function(event) {
	// Set the starting coordinates for the arrow

	if (event.target.tagName === "BUTTON") {
		startDrag = false;
	} else {
		startDrag = true;
	}

	let a = event.pageX;
	let b = event.pageY;

	let c = window.scrollX;
	let d = window.scrollY;

	let x = event.clientX;
	let y = event.clientY;

	let m = window.pageXOffset;
	let n = window.pageYOffset;



	console.log("%%%% event.clientX " + x + " event.clientY " + y);
	console.log("%%%% event.pageX " + a + " event.pageY " + b);
	console.log("%%%% window.scrollX " + c + " window.scrollY " + d);
	console.log("%%%% window.pageXOffset " + m + " window.pageXOffset " + n);


	const elementL = event.target;
	const targetRect = elementL.getBoundingClientRect();

	//const elementL = document.elementFromPoint(a, b);
	console.log("elementL elementL elementL >>>> " + elementL);
	console.log("elementL elementL elementL >>>> " + elementL.id);

	//startX = 0;
	//startY = 0;
	isDragged = false;
	if ((event.target.tagName === 'BUTTON') && (elementL.id.endsWith('L'))) {
		//startX = event.pageX;
		//startY = event.pageY;
		startX = (targetRect.left + window.scrollX);
		startY = (targetRect.top + window.scrollY);

		//startX = targetRect.left;
		//startY = targetRect.top;

		elementL.style.backgroundColor = 'red';
	}

	if (event.target.tagName === 'BUTTON') {

		//startX = event.clientX;
		//startY = event.clientY;


		//alert("Dang da da 1");

		// Event listener for when the mouse is moved
		function move(event) {
			// Set the ending coordinates for the arrow
			//endX = event.clientX;
			//endY = event.clientY;

			// Draw the arrow
			//drawArrow();
			isDragged = true;
		}



		// Event listener for when the mouse is released
		function up(event) {


			let a = event.pageX;
			let b = event.pageY;

			let c = window.scrollX;
			let d = window.scrollY;

			let x = event.clientX;
			let y = event.clientY;

			let m = window.pageXOffset;
			let n = window.pageYOffset;



			console.log("%%%% UP event.clientX " + x + " event.clientY " + y);
			console.log("%%%% UP event.pageX " + a + " event.pageY " + b);
			console.log("%%%% UP window.scrollX " + c + " window.scrollY " + d);
			console.log("%%%% UP window.pageXOffset " + m + " window.pageXOffset " + n);

			const elementR = event.target;
			const targetRect = elementR.getBoundingClientRect();
			//const elementR = document.elementFromPoint(a, b);
			console.log("elementLR elementR elementR >>>> " + elementR.id);

			// endX = event.clientX;
			// endY = event.clientY;
			document.removeEventListener("mousemove", move);
			document.removeEventListener("mouseup", up);




			if ((elementL.id.endsWith('L')) && isDragged && (elementR.id.endsWith('R'))) {

				// Set the ending coordinates for the arrow
				//endX = event.pageX;
				//endY = event.pageY;
				endX = (targetRect.left + window.scrollX);
				endY = (targetRect.top + window.scrollY);

				//endX = targetRect.left;
				//endY = targetRect.top; 

				// Draw the arrow
				drawArrow(elementL, elementR);

				// Remove the event listeners for mouse movement and release
				document.removeEventListener("mousemove", move);
				document.removeEventListener("mouseup", up);
			} else {
				// Otherwise, reset the starting coordinates for the arrow
				//startX = event.clientX;
				//startY = event.clientY;
			}
		}

		// Add the event listeners for mouse movement and release
		document.addEventListener("mousemove", move);
		document.addEventListener("mouseup", up);
	}
});
var svgg = null;
var iPathId = undefined;
function drawArrow(elementL, elementR, isCalledFromReDrawPath, pathKey) {

	console.log("svgCount :: " + svgCount)
	if (isCalledFromReDrawPath) {
		const buttonPositionL = elementL.getBoundingClientRect();
		const buttonPositionR = elementR.getBoundingClientRect();

		startX = (buttonPositionL.left + window.scrollX);
		startY = (buttonPositionL.top + window.scrollY);
		endX = (buttonPositionR.left + window.scrollX);
		endY = (buttonPositionR.top + window.scrollY);

		iPathId = "ipath" + pathKey.slice(-1);
		console.log("iPathId == " + iPathId);
	} else {
		svgCount++;
	}
	console.log("%%%% DRAW startX " + startX + " startY " + startY + " endX " + endX + " endY " + endY);
	const length = Math.sqrt((endX - startX) ** 2 + (endY - startY) ** 2);
	const angle = Math.atan2(endY - startY, endX - startX) * (180 / Math.PI);
	console.log("angle  angle " + angle);
	if (!svgg) {
		svgg = createSVG(100, 100);
	}
	const pathh = createPath(startX, startY, endX, endY, elementL, elementR, pathKey, isCalledFromReDrawPath);

	const invisibleRect = createIPath(startX, startY, endX, endY, length, angle, iPathId, isCalledFromReDrawPath);
	const markerr = createMarker();
	const arrowPathh = createArrowPath(isCalledFromReDrawPath);

	markerr.appendChild(arrowPathh);
	svgg.appendChild(markerr);
	svgg.appendChild(pathh);
	masterDiv.appendChild(invisibleRect);
	document.body.appendChild(svgg);

	finalStartX = startX;
	finalStartY = startY;
	//isCountIncrease = false;
}


//////////**************************************************************************************************************************//////////
//////////**************************************************************************************************************************//////////
//////////**************************************************************************************************************************//////////
function reDrawArrow(svgLine, markerLine, pathLine, iPathLine, midRectId, arrowPathLine, haldleLX, haldleLY, handleRX, handleRY) {
	// Create a new SVG element for the arrow
	console.log("reDrawArrow   called >>>>");
	console.log("pathLine === " + pathLine);
	const midRectLine = parent.document.getElementById(midRectId)
	pathLine.setAttribute("d", "M " + haldleLX + " " + haldleLY + " L " + handleRX + " " + handleRY);
	const length = Math.sqrt((handleRX - haldleLX) ** 2 + (handleRY - haldleLY) ** 2);
	const angle = Math.atan2(handleRY - haldleLY, handleRX - haldleLX) * (180 / Math.PI);
	const midX = (haldleLX + handleRX) / 2;
	const midY = (haldleLY + handleRY) / 2;
	//iPathLine.setAttribute("style", "position: absolute; top: " + (haldleLY + ((handleRY - haldleLY)/2)) + "px; left: " + (haldleLX+20) + "px; width: " + ((handleRX-40) - haldleLX) + "px; height: " + 8 + "px; background-color: #ff000005; z-index: 9999; transform: rotate(" + angle + "deg);");
	iPathLine.setAttribute("style", "position: absolute; top: " + (midY - 8) + "px; left: " + midX + "px; width: " + 16 + "px; height: " + 16 + "px; background-color: Red; z-index: 9999; transform: rotate(" + angle + "deg);");
	if (midRectLine) {
		midRectLine.setAttribute("style", "position: absolute; top: " + (midY + 20) + "px; left: " + (midX - (200 / 2)) + "px; width: " + 260 + "px; height: " + 150 + "px; background-color: #ff000050; z-index: -1; transform: rotate(" + 0 + "deg); ");

		var isVisible = checkVisibiity(midRectLine);
		console.log("reDrawArrow - isVisible >>> >>> >>> " + isVisible);

		if (!isVisible) {
			hideMidRect(midRectId)
			console.log("............")
		}
		hideMidRect(midRectId)
	}

	//isCountIncrease = true;
	//joinPathMap = new Map();
	//addPathToMap(startX, startY, endX, endY);	
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


var isCalledFromReDrawPath = false;

function reDrawPath(joinPathMap) {
	console.log("joinPathMap.size  :: " + joinPathMap.size)
	if (joinPathMap.size != 0) {
		//svgCount = joinPathMap.size;
	} else {
		svgCount = 0;
	}
	isCalledFromReDrawPath = true;
	createSVG(100, 100);
	var i = 0
	const pathKeys = joinPathMap.keys();
	console.log(" pathKeys.length  " + JSON.stringify(pathKeys));


	for (const pathKeyObj of pathKeys) {
		svgCount = i + 1;
		pathKey = pathKeyObj;
		console.log("pathKey:: " + pathKey)
		const handleMap = joinPathMap.get(pathKey);
		console.log("handleMap :: " + handleMap);
		const handleLLs = handleMap.keys();
		let handleLL = handleLLs.next().value;
		console.log("handleLLs :: " + handleLLs + " :: " + handleLL);
		const handleRR = handleMap.get(handleLL);
		console.log(pathKey + " <-->" + handleLL + ", " + handleRR);
		const roundL = document.getElementById(handleLL);
		const roundR = document.getElementById(handleRR);

		const buttonPositionL = roundL.getBoundingClientRect();
		const buttonPositionR = roundR.getBoundingClientRect();
		console.log("left L :: " + buttonPositionL.left + window.scrollX + " top L :: " + buttonPositionL.top)
		console.log("left R :: " + buttonPositionR.left + " top R:: " + buttonPositionR.top)


		console.log("0>------>>> pathKey " + pathKey + " roundL " + roundL.id + " roundR " + roundR.id + " pathKey.id " + pathKey + " (String(pathKey)).slice(-1) " + (String(pathKey)).slice(-1) + " ");

		/*let x = (buttonPositionL.left + window.scrollX);
		let y = (buttonPositionL.top + window.scrollY);
		let a = (buttonPositionR.left + window.scrollX);
		let b = (buttonPositionR.top + window.scrollY);


		startX = a;
		startY = b;
		endX = x;
		endY = y;*/
		drawArrow(roundL, roundR, isCalledFromReDrawPath, pathKey);
		i++;

	}

}




function createSVG(width, height) {
	console.log("svgCount >> " + svgCount);
	const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
	svg.setAttribute("width", "5000px");
	svg.setAttribute("height", "5000px");
	svg.setAttribute("style", "position:absolute; top:0; left:0; z-index:-1;");
	svg.setAttribute("id", "svg" + svgCount);

	//svgCount++;
	return svg;
}

function createPath(startX, startY, endX, endY, elementL, elementR, pathKey, isCalledFromReDrawPath) {
	const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
	path.setAttribute("d", `M ${startX} ${startY} L ${endX} ${endY}`);
	//const midX = (startX + endX) / 2;
	//const midY = (startY + endY) / 2;
	//const pathString = `M ${startX} ${startY} H ${midX} V ${endY} H ${endX}`;
	//path.setAttribute("d", pathString);

	//const length = Math.abs(endX - startX);
	//const midX = (startX + endX) / 2;
	//path.setAttribute("d", `M ${startX} ${startY} H ${midX} V ${endY} H ${endX}`);

	//path.setAttribute("d", `M ${startX} ${startY} H ${midX} V ${endY} H ${endX}`);

	path.setAttribute("stroke", "#29468c");
	path.setAttribute("stroke-width", "2");
	path.setAttribute("marker-end", "url(#markerr" + svgCount + ")");
	if (isCalledFromReDrawPath) {
		path.setAttribute("id", pathKey);
		path.setAttribute("class", "viewPath");
	}
	else {
		path.setAttribute("id", 'path' + svgCount);

	}
	console.log("svgCount ?>> " + svgCount);
	addPathToMap(startX, startY, endX, endY, elementL, elementR);
	path.setAttribute("style", "position: relative; z-index: 9999; pointer-events: all;");
	path.addEventListener('dblclick', function(event) {
		event.stopPropagation();
		console.log('Path double clicked||||||||!');
	});
	return path;
}


function createIPath(startX, startY, endX, endY, length, angle, iPathId, isCalledFromReDrawPath) {
	console.log("createIPath iPathId " + iPathId);
	const invisibleRect = document.createElement('div');
	console.log("startX " + startX);
	console.log("startY " + startY);
	console.log("endX " + endX);
	console.log("endY " + endY);
	invisibleRect.setAttribute("x", startX);
	invisibleRect.setAttribute("y", startY);
	invisibleRect.setAttribute("width", endX - startX);
	invisibleRect.setAttribute("height", endY - startY);
	invisibleRect.setAttribute("fill", "none");
	invisibleRect.setAttribute("stroke", "yellow");
	const midX = (startX + endX) / 2;
	const midY = (startY + endY) / 2;
	invisibleRect.setAttribute("style", "position: absolute; top: " + (midY - 8) + "px; left: " + midX + "px; width: " + 16 + "px; height: " + 16 + "px; background-color: #29468c; z-index: 9999; transform: rotate(" + angle + "deg);");
	if (isCalledFromReDrawPath) {
		invisibleRect.setAttribute("id", iPathId);
		invisibleRect.setAttribute("class", 'viewIPath' + ',' + 'ipath');
	}
	else {
		invisibleRect.setAttribute("id", 'ipath' + svgCount);
		invisibleRect.setAttribute("class", 'ipath');
	}
	let midRectVisible = false;
	var midRectId = "";


	invisibleRect.addEventListener('dblclick', function(event) {
		console.log("Path double clicked! invisibleRect " + invisibleRect.id);
		var invisibleRectId = invisibleRect.id;

		if (midRectVisible) {
			hideMidRect(invisibleRectId);
		} else {
			midRectId = createMidRect(startX, startY, endX, endY, length, angle, invisibleRectId, midX, midY);
		}
		const midRectLine = parent.document.getElementById(midRectId);
		var isVisible = checkVisibiity(midRectLine);
		console.log("midRectVisible " + midRectVisible + " CheckVisibility isVisible " + isVisible);
		midRectVisible = !midRectVisible;
		console.log("event.clientY " + event.clientY + " event.clientX " + event.clientX);
	});

	invisibleRect.addEventListener('contextmenu', function(event) {
		addMenu(event, invisibleRect)
	});
	return invisibleRect;
}

function createMidRect(startX, startY, endX, endY, length, angle, invisibleRectId, midX, midY) {
	const midRectId = 'midRect' + invisibleRectId.slice(-1);
	let midRect = document.getElementById(midRectId);

	if (!midRect) { // create the midRect only if it doesn't already exist
		midRect = document.createElement('div');
		midRect.setAttribute("style", "position: absolute; top: " + (midY + 20) + "px; left: " + (midX - (200 / 2)) + "px; width: " + 250 + "px; height: " + 150 + "px; background-color: #29468c; z-index: -1; transform: rotate(" + 0 + "deg); ");
		midRect.setAttribute("id", midRectId);
		midRect.classList.add("midRect");
		console.log("midRect.Id >>> >>> >>> " + midRect.id);
		createTextFieldAndLabel(midRect, 'Name:', 'name');

		document.body.appendChild(midRect);
	} else {
		midRect.style.display = 'block';
	}

	return midRectId
}






function hideMidRect(invisibleRectId) {
	const midRectId = 'midRect' + invisibleRectId.slice(-1);
	console.log("midRectId >>> >>> " + midRectId);
	const midRect = document.getElementById(midRectId);
	midRect.style.display = "none";
}
function createTextFieldAndLabel(parentElement, labelText, inputId) {
	const label = document.createElement('label');
	label.setAttribute('for', inputId);
	label.textContent = labelText;

	const input = document.createElement('input');
	input.setAttribute('type', 'text');
	input.setAttribute('id', inputId);

	parentElement.appendChild(label);
	parentElement.appendChild(input);
}


function createMarker() {
	const marker = document.createElementNS("http://www.w3.org/2000/svg", "marker");
	//marker.setAttribute("id", "xx");
	marker.setAttribute("viewBox", "0 0 10 10");
	marker.setAttribute("refX", "8");
	marker.setAttribute("refY", "5");
	marker.setAttribute("markerWidth", "6");
	marker.setAttribute("markerHeight", "6");
	marker.setAttribute("orient", 'auto-start-reverse');;
	marker.setAttribute("id", 'markerr' + svgCount);

	return marker;
}




function createArrowPath(isCalledFromReDrawPath) {
	const arrowPath = document.createElementNS("http://www.w3.org/2000/svg", "path");
	arrowPath.setAttribute("d", "M 0 0 L 10 5 L 0 10 z");
	arrowPath.setAttribute("fill", "#29468c");
	arrowPath.setAttribute("id", 'arrowPath' + svgCount);
	if (isCalledFromReDrawPath) {
		arrowPath.setAttribute("class", 'arrowPath' + ', ' + 'viewArrowPath');
	} else {
		arrowPath.setAttribute("class", 'arrowPath');
	}

	return arrowPath;
}

function getStartXnY() {
	const elements = document.getElementsByTagName('*');

	// Iterate through each element
	for (let i = 0; i < elements.length; i++) {
		const element = elements[i];

		// Check if the element is a button
		if (element.tagName === 'BUTTON') {
			// Retrieve the button's ID and position
			const buttonId = element.id;

			const buttonPosition = element.getBoundingClientRect();
			const topPosition = buttonPosition.top;
			console.log("topPosition ????????  " + topPosition);

			// Do something with the button ID and position
			console.log(`Button ID: ${buttonId}`);
			console.log(`Button position: ${buttonPosition.top}, ${buttonPosition.left}`);
		}
	}
}

// Get the element that contains the point (x, y)
function getElementFromPoint(x, y) {
	var element = document.elementFromPoint(x, y);
	while (element && element.nodeType === Node.ELEMENT_NODE) {
		var boundingRect = element.getBoundingClientRect();
		if (x >= boundingRect.left && x <= boundingRect.right &&
			y >= boundingRect.top && y <= boundingRect.bottom) {
			return element;
		}
		element = element.parentNode;
	}
	return null;
}
function getElementIdOfXY(x, y) {
	var element = getElementFromPoint(x, y);
	if (element) {
		var elementId = element.id;
		console.log("****** Element ID:  **** ", elementId);
		return elementId;
	} else {
		console.log("No element found at coordinates (x, y)");
		return null;
	}
}

function addPathToMap(startX, startY, endX, endY, elementL, elementR) {
	//let left = getElementIdOfXY(startX,startY);
	//const right = getElementIdOfXY(endX,endY);

	let left = elementL.id;
	let right = elementR.id;


	//console.log("left - " + left +" right - "+ right );


	const joinHandleMap = new Map();
	joinHandleMap.set(left, right)
	var pathName = 'path' + svgCount;
	joinPathMap.set(pathName, joinHandleMap);
	console.log("sir path map :: " + JSON.stringify(Object.fromEntries(joinPathMap)))

	createMapForSave(left, right, pathName);

	const pathKeys = joinPathMap.keys();
	console.log("addPathToMap pathKeys.length  " + pathKeys.length)
	let pathKey = pathKeys.next();

	while (!pathKey.done) {
		//console.log(pathKey.value);
		const handleMap = joinPathMap.get(pathKey.value);
		//const handleMap = new Map(Object.entries(joinPathMap.get(pathKey.value)));
		const handleLLs = handleMap.keys();
		let handleLL = handleLLs.next().value;
		//console.log("handleLL - " + handleLL  );
		const handleRR = handleMap.get(handleLL);
		console.log("PATH TO BE SAVED IN DB >--->>  " + pathKey.value + " " + handleLL + ", " + handleRR);
		pathKey = pathKeys.next();
	}
}

function createMapForSave(leftID, rightID, pathName) {
	const leftMap = new Map();
	//const rightMap = new Map();
	const pathMap = new Map();
	/*if (mapFromPathList.size > 0) {
		pathMap = mapFromPathList
		console.log("pathMap is not empty :: " + pathMap.size);
	}
	
	for (const key of pathMap.keys()) {
		if(key)
	}*/

	var leftWorkName = document.getElementById(leftID).value;
	var rightWorkName = document.getElementById(rightID).value;

	leftMap.set("leftBoxId", leftID);
	leftMap.set("leftWorkName", leftWorkName);
	leftMap.set("leftQuantity", "");
	leftMap.set("leftDescription", "Demo description");
	leftMap.set("leftPathName", pathName);
	leftMap.set("leftRate", "");

	leftMap.set("rightBoxId", rightID);
	leftMap.set("rightWorkName", rightWorkName);
	leftMap.set("rightQuantity", "");
	leftMap.set("rightDescription", "Demo description");
	leftMap.set("rightPathName", pathName);
	leftMap.set("rightRate", "");

	//divMapList.push();
	//divMapList.push( Object.fromEntries(rightMap));

	//pathMap.set(pathName, Object.fromEntries(rightMap));
	pathMap.set(pathName, Object.fromEntries(leftMap));
	pathMapList.push(Object.fromEntries(pathMap));

	console.log("pathMap :: " + JSON.stringify(Object.fromEntries(pathMap)));
	console.log("pathMap list:: " + JSON.stringify(pathMapList));
}

function checkVisibiity(element) {

	var isVisible = '';
	//const element = document.getElementById(elementId);
	const style = window.getComputedStyle(element);
	console.log("checkVisibiity - isVisible >>> >>> " + isVisible);
	if (style.display === 'none') {
		isVisible = false;
	}
	else {
		isVisible = true;
	}

	console.log("checkVisibiity - isVisible >>> >>> " + isVisible);
	return isVisible;
}

function moveBoxes(elementId, isDisplayed) {

	const toggleDiv = document.getElementById(elementId);
	var parentBoxId = toggleDiv.parentNode.id;
	console.log("<<< parentBoxId >>> " + parentBoxId);
	//const parentBoxElement =  document.getElementById(parentBoxId);

	//let nextBox = parentBoxElement.nextElementSibling;

	const totalNoOfDiv = document.getElementsByClassName("mybuttonL").length;
	console.log("totalNoOfDiv :: " + totalNoOfDiv);
	//const hiddenDivs = parentBoxElement.querySelectorAll('div[style*="display:none"]');
	//const hiddenDivs = toggleButton.querySelectorAll('div[style*="display:block"]');
	//var hiddenDivsCount = toggleDiv.querySelectorAll('div').length;

	var hiddenDivsCount = 0;

	for (var i = 0; i < toggleDiv.children.length; i++) {
		if (toggleDiv.children[i].tagName === 'DIV') {
			hiddenDivsCount++;
		}
	}

	console.log("<<< hiddenDivsCount >>> " + hiddenDivsCount);

	let currentDivIdNo = parentBoxId.slice(-2, -1);
	console.log("currentDivIdNo <====> :: " + currentDivIdNo);
	let count = (totalNoOfDiv - currentDivIdNo);
	currentDivIdNo = parseInt(currentDivIdNo) + 1;

	console.log("currentDivIdNo " + currentDivIdNo + " count " + count);


	if (!isDisplayed) {
		console.log("currentDivIdNo " + currentDivIdNo + " isDisplayed " + isDisplayed);

		for (let i = currentDivIdNo; i < count; i++) {
			//var nextBoxId = ('mybutton' + i + 'L');
			var nextBoxId = document.getElementById(parentBoxId).nextSibling
			if (nextBoxId.id != null) {
				console.log("<<< nextBoxId not displayed>>> " + nextBoxId.id + " :: " + nextBoxId.id.slice(-1));

				if ((nextBoxId.id.slice(-1)) == 'L') {
					const nextBox = document.getElementById(nextBoxId.id);
					console.log("nextBox <===> :: " + nextBox);
					if (nextBox)
						nextBox.style.top = parseInt(nextBox.style.top, 10) + toggleDiv.offsetHeight + (110 * hiddenDivsCount) + 'px';
					else
						break;
				}
				parentBoxId = nextBoxId.id;
			} else
				break;

		}
		//svgLine, markerLine, pathLine, iPathLine, midRectId, arrowPathLine, haldleLX, haldleLY, handleRX, handleRY)
		reDrawElement();
	}

	if (isDisplayed) {
		console.log("currentDivIdNo " + currentDivIdNo + " isDisplayed " + isDisplayed);

		for (let i = currentDivIdNo; i < count; i++) {
			var nextBoxId = document.getElementById(parentBoxId).nextSibling
			if (nextBoxId.id != null) {
				console.log("<<< nextBoxId not displayed>>> " + nextBoxId.id + " :: " + nextBoxId.id.slice(-1));

				if ((nextBoxId.id.slice(-1)) == 'L') {
					const nextBox = document.getElementById(nextBoxId.id);
					console.log("nextBox <===> :: " + nextBox);
					if (nextBox)
						nextBox.style.top = parseInt(nextBox.style.top, 10) - toggleDiv.offsetHeight - (110 * hiddenDivsCount) + 'px';
					else
						break;
				}
				parentBoxId = nextBoxId.id;
			} else
				break;
		}
		reDrawElement();
	}



}

function reDrawElement() {
	var svgLine = svgg;

	if (svgLine && svgLine.parentNode === document.body) {
		var markerLine = parent.document.getElementById('merkerr' + (svgCount));
		var arrowPathLine = parent.document.getElementById('arrowPath' + (svgCount));
		const pathKeys = joinPathMap.keys();
		let pathKey = pathKeys.next();
		while (!pathKey.done) {
			console.log("Path Key ===> " + pathKey);
			console.log("Path Key.Value ===> " + pathKey.value);
			const handleMap = joinPathMap.get(pathKey.value);
			const handleLLs = handleMap.keys();
			let handleLL = handleLLs.next().value;
			//console.log("handleLL - " + handleLL  );
			const handleRR = handleMap.get(handleLL);
			console.log(pathKey.value + "<====> " + handleLL + ", " + handleRR);



			const roundL = document.getElementById(handleLL);
			const roundR = document.getElementById(handleRR);

			const buttonPositionL = roundL.getBoundingClientRect();
			const buttonPositionR = roundR.getBoundingClientRect();
			pathId = pathKey.value;
			console.log("Path Id = pathKey.value ===> " + pathKey.value);
			const pathLine = parent.document.getElementById(pathId);
			const iPathId = "ipath" + pathId.slice(-1);
			console.log("iPathId == " + iPathId);
			const iPathLine = parent.document.getElementById(iPathId);
			console.log("Path Line = parent.document.getElementById(ipathId) ===> " + pathLine);
			//console.log("pathLine.tagName ===> " + pathLine.tagName);

			console.log("buttonPositionL.top " + buttonPositionL.top + " window.scrollY " + window.scrollY);
			let x = (buttonPositionL.left + window.scrollX);
			let y = (buttonPositionL.top + window.scrollY);
			console.log("haldleLX " + x + " haldleLY " + y);

			const haldleLX = x;
			const haldleLY = y;

			console.log("buttonPositionR.top " + buttonPositionR.top + " window.scrollY " + window.scrollY);
			let a = (buttonPositionR.left + window.scrollX);
			let b = (buttonPositionR.top + window.scrollY);
			console.log("handleRX " + a + " handleRY " + b);

			const handleRX = a;
			const handleRY = b;

			console.log("<////> svgLine: " + svgLine + " markerLine: " + markerLine + " pathLine: " + pathLine + " arrowPathLine: " + arrowPathLine + " haldleLX: " + haldleLX + " haldleLY: " + haldleLY + " handleRX: " + handleRX + " handleRY: " + handleRY);


			//console.log(`Button ID: ${buttonId}`);
			//console.log(`Button position: ${buttonPosition.top}, ${buttonPosition.left}`);
			const midRectId = "midRect" + pathId.slice(-1);
			console.log("midRectId >>>>>> " + midRectId);
			const midRectLine = parent.document.getElementById(midRectId);
			reDrawArrow(svgLine, markerLine, pathLine, iPathLine, midRectId, arrowPathLine, haldleLX, haldleLY, handleRX, handleRY);
			pathKey = pathKeys.next();
		}
	}
}

function addMenu(event, invisibleRect) {
	event.preventDefault();
	var invisibleRectId = invisibleRect.id;

	const path = document.getElementById('path' + (invisibleRectId.slice(-1)));
	const pathId = path.id;
	console.log("invisibleRectId " + invisibleRectId + " invisibleRect " + invisibleRect + " path " + pathId);

	let deleteOption = document.createElement('div');
	//deleteOption.classList.add('menu-option');
	//deleteOption.innerText = 'Delete';
	deleteOption.innerHTML = '<i class="fas fa-trash-alt"></i>&nbsp&nbsp&nbspDelete';
	deleteOption.style.position = 'absolute';
	deleteOption.style.top = event.pageY + 10 + 'px';
	deleteOption.style.left = event.pageX + 'px';
	deleteOption.style.border = '1px solid blue';
	deleteOption.style.padding = '5px';
	deleteOption.style.background = 'white'
	console.log("event.clientY " + event.clientY + " event.clientX " + event.clientX);

	/*let otherOption = document.createElement('div');
	//otherOption.classList.add('menu-option');
	otherOption.style.position = 'absolute';
	otherOption.innerHTML = '<i class="fas fa-cog"></i> Other Option';
	otherOption.style.left = event.clientX + 'px';
	otherOption.style.top = (event.clientY + 30) + 'px';
	otherOption.style.border = '1px solid blue';
	otherOption.style.padding = '5px';
	otherOption.style.background = 'white'*/

	document.body.appendChild(deleteOption);
	//document.body.appendChild(otherOption);

	// Add a click event listener to the delete option menu
	deleteOption.addEventListener('click', function(event) {
		event.stopPropagation();
		invisibleRect.remove();
		deleteOption.remove();
		joinPathMap.delete(pathId);
		path.parentNode.removeChild(path);
	});

	// Add a click event listener to the window object
	window.addEventListener('click', function(event) {
		deleteOption.remove();

	});

}