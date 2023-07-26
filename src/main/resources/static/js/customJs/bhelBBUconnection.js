var startX = '';
var startY = '';

var finalStartX = '';
var finalStartY = '';
var svgLine = '';
let svgCount = 0;

var offsetHeight;
var offsetHeightFooter;

var invisibleRectId = '';
// Create a new Map
var joinPathMap = new Map();
var isFromViewWork = false;

let pathMapList = new Array();
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
	let x;
	let y;
	let a;
	let b;
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
			x = (buttonPositionL.left + window.scrollX);
			y = (buttonPositionL.top + window.scrollY);
			console.log("haldleLX " + x + " haldleLY " + y);



			console.log("buttonPositionR.top " + buttonPositionR.top + " window.scrollY " + window.scrollY);
			a = (buttonPositionR.left + window.scrollX);
			b = (buttonPositionR.top + window.scrollY);
			console.log("handleRX " + a + " handleRY " + b);

			const haldleLX = x;
			const haldleLY = y;

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
		console.log("In toggleVisibility for view Element Name: " + element + "elementId:  " + elementId);

		if (element.style.display === "none" && isFromViewWork) {
			element.style.display = "block";
			moveBoxes(elementId, isDisplayed);
		}

	}
};

function toggleVisibility(elementId) {
	var element = document.getElementById(elementId);
	console.log("In toggleVisibility Element Name: " + element + "elementId:  " + elementId);
	var isDisplayed = false;

	if (element == null) {
		//element.parent.style.backgroundColor = '#29468c';
		alert("This item has no sub-items")
	}
	else if (element != null && element.style.display === "none") {
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
function isChildExist(targetId) {
	let hasChildDivs = false;
	if (document.getElementById(targetId) != null) {
		let lastChildId = document.getElementById(targetId).parentNode.lastChild.id;
		console.log("last Child parent :: ", lastChildId);
		console.log("last Child Id :: ", event.target.id);
		if (lastChildId != null && lastChildId.startsWith("hiddenContainerDiv")) {
			hasChildDivs = true;
		} else {
			hasChildDivs = false;
		}
	}
	return hasChildDivs;
}

// Event listener for when the mouse is pressed down on the first button
document.addEventListener("mousedown", function(event) {
	// Set the starting coordinates for the arrow
	let hasChildDivs = isChildExist(event.target.id);


	if (event.target.tagName === "BUTTON") {
		//console.log("tagName",event.target.tagName);

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

		elementL.style.backgroundColor = 'red';
	}

	if (event.target.tagName === 'BUTTON') {
		// Event listener for when the mouse is moved
		function move(event) {
			if (!hasChildDivs) {
				isDragged = true;
			} else {
				elementL.style.backgroundColor = '#29468c';
				console.log("draw not allowed");
				//if(!alert('Your data has been saved.')){window.location.reload();}
			}
		}

		// Event listener for when the mouse is released
		function up(event) {

			const elementR = event.target;
			const targetRect = elementR.getBoundingClientRect();
			//const elementR = document.elementFromPoint(a, b);
			console.log("elementLR elementR elementR >>>> " + elementR.id);

			// endX = event.clientX;
			// endY = event.clientY;
			document.removeEventListener("mousemove", move);
			document.removeEventListener("mouseup", up);
			if ((elementL.id.endsWith('L')) && isDragged && (elementR.id.endsWith('R'))) {

				endX = (targetRect.left + window.scrollX);
				endY = (targetRect.top + window.scrollY);

				// Draw the arrow
				drawArrow(elementL, elementR);

				// Remove the event listeners for mouse movement and release
				document.removeEventListener("mousemove", move);
				document.removeEventListener("mouseup", up);
			}
		}

		// Add the event listeners for mouse movement and release
		document.addEventListener("mousemove", move);
		document.addEventListener("mouseup", up);
	}
});
var svgg = null;
var iPathId = undefined;
function createInFlowOutFlow(leftID, rightID) {
	var createInFlowOutFlowMap = new Map();
	var leftAmmount = undefined;
	var rightAmmount = undefined;
	var leftDesc = undefined;
	var rightDesc = undefined;
	var rightRate = undefined;
	var leftRate = undefined;
	var leftQty = undefined;
	var rightQty = undefined;

	var nextDivL = document.getElementById(leftID).nextSibling;
	//var children = parent.querySelectorAll('.labell');
	var nextDivChildrenL = document.getElementById(nextDivL.id);
	console.log("next Div ChildrenL  :: " + nextDivChildrenL.childNodes[0].className);
	var spanIdArrayL = " ";
	for (var childL of nextDivChildrenL.childNodes[0].childNodes) {
		//console.log("childL id :: " + childL.id);
		var spanId = childL.id;
		if (spanId != undefined && spanId.startsWith("qty") && spanId.endsWith("L")) {
			console.log("child qty id :: " + spanId);
			console.log("child qty value :: " + document.getElementById(spanId).innerHTML);
			leftQty = document.getElementById(spanId).innerHTML;

		} else if (spanId != undefined && spanId.startsWith("amt") && spanId.endsWith("L")) {
			console.log("child ammount id :: " + spanId);
			console.log("child ammount value :: " + document.getElementById(spanId).innerHTML);
			leftAmmount = document.getElementById(spanId).innerHTML;

		} else if (spanId != undefined && spanId.startsWith("desc") && spanId.endsWith("L")) {
			console.log("child desc id :: " + spanId);
			console.log("child desc value :: " + document.getElementById(spanId).innerHTML);
			leftDesc = document.getElementById(spanId).innerHTML;

		} else if (spanId != undefined && spanId.startsWith("rate") && spanId.endsWith("L")) {
			console.log("child rate id :: " + spanId);
			console.log("child rate value :: " + document.getElementById(spanId).innerHTML);
			leftRate = document.getElementById(spanId).innerHTML;

		}
		if (spanId != undefined && !spanId.endsWith("Lb")) {
			console.log("childR id :: " + spanId);
			spanIdArrayL += "," + spanId;
		}
	}
	console.log("spanIdArrayL :: " + spanIdArrayL);

	var spanIdArrayR = " ";
	console.log("right id :: " + rightID);
	var nextDivR = document.getElementById(rightID).nextSibling;
	var nextDivChildrenR = document.getElementById(nextDivR.id);
	console.log("nextDivChildrenR id :: " + nextDivChildrenR.id);
	for (var childR of nextDivChildrenR.childNodes) {
		var childId = childR.id;

		if (childId != undefined && childId.startsWith("qty") && childId.endsWith("R")) {
			console.log("childR qty id :: " + childId);
			console.log("childR qty value :: " + document.getElementById(childId).innerHTML);
			rightQty = document.getElementById(childId).innerHTML;

		} else if (childId != undefined && childId.startsWith("amt") && childId.endsWith("R")) {
			console.log("childR ammount id :: " + childId);
			console.log("childR ammount value :: " + document.getElementById(childId).innerHTML);
			rightAmmount = document.getElementById(childId).innerHTML;

		} else if (childId != undefined && childId.startsWith("desc") && childId.endsWith("R")) {
			console.log("childR desc id :: " + childId);
			console.log("childR desc value :: " + document.getElementById(childId).innerHTML);
			rightDesc = document.getElementById(childId).innerHTML;

		} else if (childId != undefined && childId.startsWith("rate") && childId.endsWith("R")) {
			console.log("childR rate id :: " + childId);
			console.log("childR rate value :: " + document.getElementById(childId).innerHTML);
			rightRate = document.getElementById(childId).innerHTML;

		}
		if (childId != undefined && !childId.endsWith("LbR")) {
			console.log("childR id :: " + childId);
			spanIdArrayR += "," + childId;
		}

	}

	createInFlowOutFlowMap.set("rightAmount", rightAmmount);
	createInFlowOutFlowMap.set("leftIdArray", spanIdArrayL);
	createInFlowOutFlowMap.set("rightIdArray", spanIdArrayR);
	createInFlowOutFlowMap.set("leftAmount", leftAmmount);
	createInFlowOutFlowMap.set("leftRate", leftRate);
	createInFlowOutFlowMap.set("leftDesc", leftDesc);
	createInFlowOutFlowMap.set("rightDesc", rightDesc);
	createInFlowOutFlowMap.set("leftQty", leftQty);
	createInFlowOutFlowMap.set("rightQty", rightQty);
	createInFlowOutFlowMap.set("rightRate", rightRate);
	createInFlowOutFlowMap.set("spanIdArrayL", spanIdArrayL.replace(' ,', ''));
	createInFlowOutFlowMap.set("spanIdArrayR", spanIdArrayR.replace(' ,', ''));

	console.log("rightAmmount :: " + createInFlowOutFlowMap.size);

	return createInFlowOutFlowMap;


}
function calculateAmount(data) {
	let rightAmmount = 0;
	let leftAmmount = 0;
	let cumleftAmmount = 0;
	let handleMap;
	let enteredValue = data.value;
	let inputId = data.id;
	const idArray = inputId.split("_");
	let pathId = idArray[1];
	let joinPathMapUpdate;
	let pathMapListNew = [...pathMapList];
	let pathMapListUp = [];
	for (let pathMapObj of pathMapListNew) {
		//console.log("pathMap  " + pathMap);
		let pathMap = new Map(Object.entries(pathMapObj));
		if (pathMap != null) {
			let pathKeys = pathMap.keys();
			joinPathMapUpdate = new Map();
			for (let pathKeyObj of pathKeys) {

				console.log("pathKeyObj :: " + pathKeyObj);
				if (pathKeyObj != pathId) {
					handleMap = new Map(Object.entries(pathMap.get(pathKeyObj)));
					console.log("if handel path  " + JSON.stringify(Object.fromEntries(handleMap)));
				} else if (pathKeyObj == pathId) {
					handleMap = new Map(Object.entries(pathMap.get(pathId)));
					console.log("handleMap  " + JSON.stringify(Object.fromEntries(handleMap)));
					leftAmmount = handleMap.get("leftAmmount");
					console.log("left Ammount :: ", leftAmmount);
					//rightAmmount = 2000;
					if (leftAmmount != 0 && enteredValue.endsWith("%")) {
						cumleftAmmount = leftAmmount * enteredValue.substring(0, enteredValue.length - 1) / 100;
						//rightAmmount = leftAmmount;
						console.log("cumleftAmmount :: ", cumleftAmmount);
					}
					let idStringArr = handleMap.get("spanIdArrayL").split(",");
					let rightIdStringArr = handleMap.get("spanIdArrayR").split(",");
					for (let idString of idStringArr) {
						if (idString != '' && idString.startsWith("amt") && idString.endsWith("L")) {
							console.log("idString :: ", idString);
							console.log("cumleftAmmount :: ", cumleftAmmount);
							let amtDiv = document.getElementById(idString);
							let cumAmountLb = document.createElement("label");
							cumAmountLb.id = "cum" + idString + 'b';
							cumAmountLb.innerHTML = "Completed Ammount:"
							cumAmountLb.style = "font-weight:bold";
							let cumAmount = document.createElement("span");
							cumAmount.id = "cum" + idString;
							cumAmount.innerHTML = cumleftAmmount;
							console.log(amtDiv.parentNode);
							amtDiv.parentNode.append(document.createElement("br"));
							amtDiv.parentNode.append(cumAmountLb);
							amtDiv.parentNode.append(cumAmount);

						}

					}
					for (let rIdString of rightIdStringArr) {
						if (rIdString != '' && rIdString.startsWith("amt") && rIdString.endsWith("R")) {
							let rightAmt = document.getElementById(rIdString).innerHTML;
							console.log("rightAmt is zero :", rightAmt)
							if (rightAmt == 0) {
								document.getElementById(rIdString).innerHTML = cumleftAmmount;
							}
						}
					}
					handleMap.set("cumAmount", cumleftAmmount);
					handleMap.set("workDone", enteredValue);


					//console.log("calculate Amount pathMap :: " + JSON.stringify(Object.fromEntries(pathMap)))
					console.log("right Ammount ufter calc:: " + cumleftAmmount);
					//console.log("else handel path  " + JSON.stringify(Object.fromEntries(handleMap)));
				}
				joinPathMapUpdate.set(pathKeyObj, Object.fromEntries(handleMap));
				//console.log("calculate Amount joinPathMapUpdate :: " + JSON.stringify(Object.fromEntries(joinPathMapUpdate)))
				//console.log("calculate Amount joinPathMapUpdate :: " + JSON.stringify(Object.fromEntries(joinPathMapUpdate)))
			}
			pathMapListUp.push(Object.fromEntries(joinPathMapUpdate))
			//console.log("calculate Amount pathMapList :: " + JSON.stringify(pathMapListUp));
		}
		//createMapForSave(handleMap.get("leftBoxId"), handleMap.get("rightBoxId"), handleMap.get("leftPathName"), enteredValue);
	}
	pathMapList = [...pathMapListUp];
	createRightSidePane(pathMapList);
	//console.log("path Map List from calculateAmount :: " + JSON.stringify(pathMapList));
}

function drawArrow(elementL, elementR, isCalledFromReDrawPath, pathKey) {

	let pageXOffsetVal = window.pageXOffset;
	let pageYOffsetVal = window.pageYOffset;

	console.log("svgCount :: " + svgCount)
	if (isCalledFromReDrawPath) {
		const buttonPositionL = elementL.getBoundingClientRect();
		const buttonPositionR = elementR.getBoundingClientRect();

		startX = ((buttonPositionL.left + window.scrollX) - pageXOffsetVal);
		startY = ((buttonPositionL.top + window.scrollY) - pageYOffsetVal);
		endX = ((buttonPositionR.left + window.scrollX) - pageXOffsetVal);
		endY = ((buttonPositionR.top + window.scrollY) - pageYOffsetVal);

		/*startX = (buttonPositionL.left + window.scrollX);
		startY = (buttonPositionL.top + window.scrollY);
		endX = (buttonPositionR.left + window.scrollX);
		endY = (buttonPositionR.top + window.scrollY);*/

		iPathId = "ipath" + pathKey.slice(-1);
		savediPathId = iPathId;
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

	const invisibleRect = createIPath(startX, startY, endX, endY, length, angle, iPathId, pathh.id, isCalledFromReDrawPath);


	const markerr = createMarker();
	const arrowPathh = createArrowPath(isCalledFromReDrawPath);

	markerr.appendChild(arrowPathh);
	svgg.appendChild(markerr);
	svgg.appendChild(pathh);
	masterDiv.appendChild(invisibleRect);
	document.body.appendChild(svgg);

	if (isCalledFromReDrawPath) {
		let workDoneDiv = createWorkDoneInput(startX, startY, endX, endY, length, pathh.id, invisibleRect.id, angle);
		if (workDoneDiv != null) {
			console.log("workDoneDiv.id : ", workDoneDiv);
			document.getElementById(workDoneDiv).style.display = "none";
		}
	}

	finalStartX = startX;
	finalStartY = startY;
	if (isCalledFromReDrawPath) {
		document.getElementById(iPathId).style.backgroundColor = "#26ab80";
	}
	//isCountIncrease = false;
}

function createWorkDoneInput(startX, startY, endX, endY, length, pathhId, invisibleRectId, angle) {

	const midX = (startX + endX) / 2;
	const midY = (startY + endY) / 2;
	console.log("Path double clicked! invisibleRect " + invisibleRectId);
	//invisibleRectId = invisibleRect.id;

	midRectId = createMidRect(startX, startY, endX, endY, length, angle, pathhId, invisibleRectId, midX, midY);
	const midRectLine = parent.document.getElementById(midRectId);
	var isVisible = checkVisibiity(midRectLine);
	console.log(" CheckVisibility isVisible " + isVisible);
	return midRectId;
}
//////////**************************************************************************************************************************//////////
//////////**************************************************************************************************************************//////////
//////////**************************************************************************************************************************//////////
function reDrawArrow(svgLine, markerLine, pathLine, iPathLine, midRectId, arrowPathLine, haldleLX, haldleLY, handleRX, handleRY) {
	// Create a new SVG element for the arrow
	console.log("reDrawArrow   called >>>>");
	console.log("pathLine === " + pathLine);

	const midRectLine = parent.document.getElementById(midRectId);
	pathLine.setAttribute("d", "M " + haldleLX + " " + haldleLY + " L " + handleRX + " " + handleRY);

	let haldleLY_1 = haldleLY - offsetHeight;
	let haldleRY_1 = handleRY - offsetHeight;

	const length = Math.sqrt((handleRX - haldleLX) ** 2 + (handleRY - haldleLY) ** 2);
	const angle = 0;
	//const angle = Math.atan2(handleRY_1 - haldleLY_1, handleRX - haldleLX) * (180 / Math.PI);
	const midX = (haldleLX + handleRX) / 2;
	const midY = (haldleLY_1 + haldleRY_1) / 2;
	//iPathLine.setAttribute("style", "position: absolute; top: " + (haldleLY + ((handleRY - haldleLY)/2)) + "px; left: " + (haldleLX+20) + "px; width: " + ((handleRX-40) - haldleLX) + "px; height: " + 8 + "px; background-color: #ff000005; z-index: 9999; transform: rotate(" + angle + "deg);");
	iPathLine.setAttribute("style", "position: absolute; top: " + (midY) + "px; left: " + midX + "px; width: " + 16 + "px; height: " + 16 + "px; background-color: #29468c; z-index: 9999; transform: rotate(" + angle + "deg);");
	if (midRectLine) {
		midRectLine.setAttribute("style", "position: absolute; top: " + (midY + 20) + "px; left: " + (midX - (200 / 2)) + "px; width: " + 260 + "px; height: " + 150 + "px; /*background-color: #ff000050; z-index: -1*/; transform: rotate(" + angle + "deg); ");

		var isVisible = checkVisibiity(midRectLine);
		console.log("reDrawArrow - isVisible >>> >>> >>> " + isVisible);

		if (!isVisible) {
			hideMidRect(midRectId)
			console.log("............")
		}
		//hideMidRect(midRectId)
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


		drawArrow(roundL, roundR, isCalledFromReDrawPath, pathKey);
		i++;

	}

}




function createSVG(width, height) {
	console.log("svgCount >> " + svgCount);
	const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
	svg.setAttribute("width", "5000px");
	svg.setAttribute("height", "25000px");
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
		//path.setAttribute("class", "viewPath");
		path.classList.add("viewPath");
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


function createIPath(startX, startY, endX, endY, length, angle, iPathId, pathId, isCalledFromReDrawPath) {
	console.log("createIPath iPathId " + iPathId);
	const invisibleRect = document.createElement('div');
	let startY_1 = startY - offsetHeight;
	let endY_1 = endY - offsetHeight;
	console.log("createIPath startX " + startX);
	console.log("createIPath startY " + startY_1);
	console.log("createIPath endX " + endX);
	console.log("createIPath endY " + endY_1);

	/*invisibleRect.setAttribute("x", startX);
	invisibleRect.setAttribute("y", startY);*/

	invisibleRect.setAttribute("x", startX);
	invisibleRect.setAttribute("y", startY);
	invisibleRect.setAttribute("width", endX - startX);
	//invisibleRect.setAttribute("height", endY - startY);
	invisibleRect.setAttribute("height", endY_1 - startY_1);
	invisibleRect.setAttribute("fill", "none");
	invisibleRect.setAttribute("stroke", "yellow");
	/*const midX = (startX + endX) / 2;
	const midY = (startY + endY) / 2;*/

	const midX = (startX + endX) / 2;
	const midY = (startY_1 + endY_1) / 2;
	console.log("createIPath midX :: " + midX);
	console.log("createIPath midY :: " + midY);
	invisibleRect.setAttribute("style", "position: absolute; top: " + (midY) + "px; left: " + midX + "px; width: " + 16 + "px; height: " + 16 + "px; background-color: #29468c; z-index: 9999; transform: rotate(" + 0 + "deg);");
	if (isCalledFromReDrawPath) {
		invisibleRect.setAttribute("id", iPathId);
		//invisibleRect.setAttribute("class", 'viewIPath' + ',' + 'ipath');
		invisibleRect.classList.add('viewIPath');
		invisibleRect.classList.add('ipath');
	}
	else {
		invisibleRect.setAttribute("id", 'ipath' + svgCount);
		//invisibleRect.setAttribute("class", 'ipath');
		invisibleRect.classList.add('ipath');
	}
	let midRectVisible = false;
	var midRectId = "";


	invisibleRect.addEventListener('dblclick', function(event) {
		console.log("Path double clicked! invisibleRect " + invisibleRect.id);
		invisibleRectId = invisibleRect.id;
		var st = window.getComputedStyle(invisibleRect, null);

		var angle = 0;

		if (!midRectVisible) {
			//hideMidRect(invisibleRectId);
			//} else {
			midRectId = createMidRect(startX, startY_1, endX, endY, length, angle, pathId, invisibleRectId, midX, midY);
		}
		const midRectLine = parent.document.getElementById(midRectId);
		var isVisible = checkVisibiity(midRectLine);
		console.log("midRectVisible " + midRectVisible + " CheckVisibility isVisible " + isVisible);
		//midRectVisible = !midRectVisible;
		console.log("event.clientY " + event.clientY + " event.clientX " + event.clientX);
	});

	invisibleRect.addEventListener('contextmenu', function(event) {
		addMenu(event, invisibleRect)
	});
	return invisibleRect;
}

function createMidRect(startX, startY, endX, endY, length, angle, pathId, invisibleRectId, midX, midY) {
	const midRectId = 'midRect' + invisibleRectId.slice(-1);
	let midRect = document.getElementById(midRectId);
	console.log("createMidRect path :: " + pathId);
	console.log("createMidRect midx :: " + midX);
	console.log("createMidRect midy :: " + midY);
	if (!midRect) { // create the midRect only if it doesn't already exist
		midRect = document.createElement('div');
		midRect.setAttribute("style", "position: absolute; top: " + (midY + 30) + "px; left: " + (midX - (100 / 2)) + "px; width: " + 250 + "px; height: " + 150 + "px; background-color: #29468c; z-index: 10000; transform: rotate(" + 0 + "deg); ");
		midRect.setAttribute("id", midRectId);
		midRect.classList.add("midRect");
		console.log("midRect.Id >>> >>> >>> " + midRect.id);
		createTextFieldAndLabel(midRect, 'Work done', 'wd_' + pathId);
		createButton(midRect, '<i class="fa fa-times"></i>', 'close' + midRectId, midRectId)


		document.body.appendChild(midRect);
	} else {
		midRect.style.display = 'block';
	}

	return midRectId
}






/*function hideMidRect() {
	const midRectId = 'midRect' + invisibleRectId.slice(-1);
	console.log("midRectId >>> >>> " + midRectId);
	const midRect = document.getElementById(midRectId);
	midRect.style.display = "none";
}*/
function hideMidRectFrombtn(btn) {

	const midRectId = btn.getAttribute("data-mid-rect-id");
	console.log("midRectId >>> >>> " + midRectId);
	const midRect = document.getElementById(midRectId);
	midRect.style.display = "none";
}
function createTextFieldAndLabel(parentElement, labelText, inputId) {
	/*const label = document.createElement('label');
	label.setAttribute('for', inputId);
	label.textContent = labelText;*/

	const input = document.createElement('input');
	input.setAttribute('type', 'text');
	input.setAttribute('id', inputId);
	input.setAttribute('placeholder', labelText);
	input.setAttribute('onchange', 'calculateAmount(this)');

	//parentElement.appendChild(label);
	parentElement.appendChild(input);
}
function createButton(parentElement, name, id, midRectId) {
	const btn = document.createElement('button');
	btn.id = id;
	btn.innerHTML = name;
	btn.className = "btn btn-danger midRectCloseBtn";
	btn.dataset.midRectId = midRectId;
	//btn.className="midRectCloseBtn";
	btn.setAttribute("onclick", "hideMidRectFrombtn(this)");
	parentElement.appendChild(btn);
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

let pathMap;
function createMapForSave(leftID, rightID, pathName) {
	/*if (enteredValue != null) {
		pathMapList = undefined;
		console.log("called form calculateAmount :: " + leftID + " " + rightID + " " + pathName + " " + enteredValue)
	}*/
	var returnVal = createInFlowOutFlow(leftID, rightID);
	var returnValMap = new Map(returnVal);
	console.log("returnVal   :: " + returnVal.size);
	console.log("returnVal json:: " + JSON.stringify(Object.fromEntries(returnVal)));

	console.log("returnVal map size:: " + returnValMap.size);
	console.log("returnVal map json:: " + JSON.stringify(Object.fromEntries(returnValMap)));

	//var splitReturnVal = returnVal.split(',');

	const leftMap = new Map();
	//const rightMap = new Map();
	pathMap = new Map();



	var leftWorkName = document.getElementById(leftID).value;
	var rightWorkName = document.getElementById(rightID).value;
	console.log("leftRate :: " + returnValMap.get("leftRate"));

	leftMap.set("leftBoxId", leftID);
	leftMap.set("leftWorkName", leftWorkName);
	leftMap.set("leftQuantity", returnValMap.get("leftQty"));
	leftMap.set("leftDescription", returnValMap.get("leftDesc"));
	leftMap.set("leftPathName", pathName);
	leftMap.set("leftRate", returnValMap.get("leftRate"));
	leftMap.set("leftAmmount", returnValMap.get("leftAmount"));
	leftMap.set("spanIdArrayL", returnValMap.get("spanIdArrayL"));
	leftMap.set("workDone", "0%");


	leftMap.set("rightBoxId", rightID);
	leftMap.set("rightWorkName", rightWorkName);
	leftMap.set("rightQuantity", returnValMap.get("rightQty"));
	leftMap.set("rightDescription", returnValMap.get("rightDesc"));
	leftMap.set("rightPathName", pathName);
	leftMap.set("rightRate", returnValMap.get("rightRate"));
	leftMap.set("rightAmmount", returnValMap.get("rightAmount"));
	leftMap.set("spanIdArrayR", returnValMap.get("spanIdArrayR"));

	//divMapList.push();
	//divMapList.push( Object.fromEntries(rightMap));
	let pathMapListUpdate = [];
	//pathMap.set(pathName, Object.fromEntries(rightMap));
	if (pathMapList.length > 0) {
		for (var pathMap of pathMapList) {
			var joinPathMap = new Map(Object.entries(pathMap))
			if (joinPathMap != null) {
				joinPathMap.set(pathName, Object.fromEntries(leftMap));
			}
			console.log("pathMap :: " + JSON.stringify(Object.fromEntries(joinPathMap)));
			pathMapListUpdate.push(Object.fromEntries(joinPathMap));
		}
		pathMapList = [...pathMapListUpdate];
	} else {
		pathMap.set(pathName, Object.fromEntries(leftMap));
		pathMapList.push(Object.fromEntries(pathMap));
		console.log("pathMap :: " + JSON.stringify(Object.fromEntries(pathMap)));
	}


	console.log("pathMap list:: " + JSON.stringify(pathMapList));
	createRightSidePane(pathMapList);
}
function createRightSidePane(pathMapList) {
	var totalLeftAmmount = 0;
	var totalLeftAmmountEC = 0;
	var totalRightAmmount = 0;
	var totalCumAmmount = 0;
	const tbl = document.getElementById("amountTableBody");
	const tblFooter = document.getElementById("amountTableBodyFooter");
	const ectbl = document.getElementById("ecTableBody");
	const ectblFooter = document.getElementById("ecTableBodyFooter");
	tbl.innerHTML = "";
	tblFooter.innerHTML = "";
	ectbl.innerHTML = "";
	ectblFooter.innerHTML = "";
	let ecData = false;
	let enteredValue = undefined;
	for (var pathMap of pathMapList) {
		//console.log("joinPathMap  " + pathMap);
		var joinPathMap = new Map(Object.entries(pathMap))
		console.log("joinPathMap  " + JSON.stringify(Object.fromEntries(joinPathMap)));
		const pathKeys = joinPathMap.keys();
		//console.log("createRightSidePane pathKeys  " + JSON.stringify(pathKeys));
		//console.log("createRightSidePane pathKeys  " + JSON.stringify(Object.entries(pathKeys)));


		for (const pathKeyObj of pathKeys) {
			var newRow = document.createElement("tr");
			console.log("createRightSidePane pathKeys  " + pathKeyObj);
			//svgCount = i + 1;
			pathKey = pathKeyObj;
			const handleMap = new Map(Object.entries(joinPathMap.get(pathKey)));
			console.log("handel path  " + JSON.stringify(Object.fromEntries(handleMap)));


			enteredValue = handleMap.get("workDone");
			console.log("work done =", enteredValue);
			if (parseInt(enteredValue.substring(0, enteredValue.length - 1)) > 0) {
				ecData = true;
			} else {
				ecData = false;
			}
			let leftAmmount = undefined;
			let rightAmmount = undefined;
			let ecleftAmmount = undefined;
			if (ecData) {
				const cumuAmount = handleMap.get("cumAmount");
				ecleftAmmount = handleMap.get("leftAmmount")
				totalCumAmmount = totalCumAmmount + parseInt(cumuAmount);
				console.log("total Cum Ammount  " + totalCumAmmount);
				totalLeftAmmountEC = totalLeftAmmount + parseInt(ecleftAmmount);
			}
			else {
				leftAmmount = handleMap.get("leftAmmount")
				rightAmmount = handleMap.get("rightAmmount");
				totalRightAmmount = totalRightAmmount + parseInt(rightAmmount);
				console.log("totalRightAmmount  " + totalRightAmmount);
				totalLeftAmmount = totalLeftAmmount + parseInt(leftAmmount);
			}



			console.log("leftAmmount  " + leftAmmount);
			console.log("rightAmmount  " + rightAmmount);
			console.log("totalLeftAmmount  " + totalLeftAmmount);

			//var newRow = document.createElement("tr");
			var newCell_1 = document.createElement("td");
			var newCell_2 = document.createElement("td");
			var newCell_3 = document.createElement("td");
			var newCell_4 = document.createElement("td");
			var newCell_5 = document.createElement("td");
			var newCell_6 = document.createElement("td");

			newCell_1.innerHTML = handleMap.get("leftDescription");
			newCell_2.innerHTML = handleMap.get("leftQuantity");
			newCell_3.innerHTML = leftAmmount;
			if (!ecData) {
				newCell_4.innerHTML = handleMap.get("rightDescription");
				newCell_3.innerHTML = leftAmmount;
			}
			else {
				newCell_3.innerHTML = ecleftAmmount;
				newCell_4.innerHTML = handleMap.get("workDone");
			}
			newCell_5.innerHTML = handleMap.get("rightQuantity");

			if (ecData)
				newCell_6.innerHTML = handleMap.get("cumAmount");
			else
				newCell_6.innerHTML = rightAmmount;


			newRow.append(newCell_1);
			newRow.append(newCell_2);
			newRow.append(newCell_3);
			newRow.append(newCell_4);
			newRow.append(newCell_5);
			newRow.append(newCell_6);
			console.log("ecData :: ", ecData);
			if (ecData) {
				console.log("updating ec table")
				document.getElementById("ecTableBody").appendChild(newRow);
			}
			else {
				console.log("updating report table")
				tbl.appendChild(newRow);
			}


		}

	}
	if (totalCumAmmount != 0) {
		ecData = true
	}
	var newRowFooter = document.createElement("tr");
	var newCell_1_f = document.createElement("th");
	var newCell_2_f = document.createElement("th");
	var newCell_3_f = document.createElement("th");
	var newCell_4_f = document.createElement("th");
	var newCell_5_f = document.createElement("th");
	var newCell_6_f = document.createElement("th");

	newCell_1_f.innerHTML = "Total";
	newCell_2_f.innerHTML = "";

	newCell_4_f.innerHTML = "Total";
	newCell_5_f.innerHTML = "";
	if (ecData) {
		newCell_3_f.innerHTML = totalLeftAmmountEC;
		newCell_6_f.innerHTML = totalCumAmmount;
	}
	else {
		newCell_6_f.innerHTML = totalRightAmmount;
		newCell_3_f.innerHTML = totalLeftAmmount;
	}

	newRowFooter.append(newCell_1_f);
	newRowFooter.append(newCell_2_f);
	newRowFooter.append(newCell_3_f);
	newRowFooter.append(newCell_4_f);
	newRowFooter.append(newCell_5_f);
	newRowFooter.append(newCell_6_f);

	if (ecData) {
		document.getElementById("ecTableBodyFooter").appendChild(newRowFooter);
	}
	else {
		document.getElementById("amountTableBodyFooter").appendChild(newRowFooter);
	}

	if (!ecData) {
		document.getElementById("totalCustomerAmount").innerHTML = "Rs. " + totalLeftAmmount;
		document.getElementById("totalVendorAmount").innerHTML = "Rs. " + totalRightAmmount;
	} else {
		document.getElementById("totalECCustomerAmount").innerHTML = "Rs. " + totalLeftAmmount;
		document.getElementById("totalWamVendorAmount").innerHTML = "Rs. " + totalCumAmmount;
	}

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
	let totalNoOfDiv = undefined;
	const toggleDiv = document.getElementById(elementId);
	let parentBox = toggleDiv.parentNode;
	let parentBoxId = parentBox.id;
	let parentBoxClass = parentBox.className;
	console.log("<<< parent Box classname >>> " + parentBoxClass);
	console.log("<<< parent Box Id >>> " + parentBoxId);

	let superParentBox = parentBox.parentNode;
	console.log("<<< super Parent Box Id >>> " + superParentBox.id);

	if (superParentBox.id != 'masterDiv') {
		//const totalNoOfDiv = document.getElementsByClassName(parentBoxClass).length;
		totalNoOfDiv = document.getElementById(superParentBox.id).childNodes.length;

	} else {
		totalNoOfDiv = document.getElementsByClassName(parentBoxClass).length;
	}
	console.log("totalNoOfDiv :: " + totalNoOfDiv);
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

			//console.log("<<< nextBox Id >>> " + nextBoxId.id);
			if (nextBoxId != null) {
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
			if (nextBoxId != null) {
				console.log("<<< nextBoxId  displayed>>> " + nextBoxId.id + " :: " + nextBoxId.id.slice(-1));

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
	deleteOption.innerHTML = '<i class="fas fa-trash-alt"></i>&nbspDelete';
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
		//for (var pathMap of pathMapList) {
		pathMapList.forEach(function(pathMap, index, object) {
			//console.log("joinPathMap  " + pathMap);
			var pathMapFromList = new Map(Object.entries(pathMap))

			const pathKeys = pathMapFromList.keys();

			for (const pathKeyObj of pathKeys) {
				console.log("pathId and pathKeyObj  " + pathKeyObj + "--" + pathId);
				if (pathKeyObj == pathId) {
					//pathMapFromList.delete(pathId);
					object.splice(index, 1);
				}
			}
		})
		console.log("pathMapList  " + pathMapList);
		createRightSidePane(pathMapList);
		path.parentNode.removeChild(path);
	});

	// Add a click event listener to the window object
	window.addEventListener('click', function(event) {
		deleteOption.remove();

	});

}