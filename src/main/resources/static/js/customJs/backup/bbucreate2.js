/*/**
 * 
 */

/*let div = document.createElement("DIV");

div.id = 'mybuttonL';
$('#masterDiv1').append(div);

function createButton() {
	let btn = document.createElement("button");

	btn.classList.add("btn", "btn-success");
	btn.innerHTML = "Reinitiate";
	btn.type = "button";
	btn.name = "reinitiatemBtn";
	btn.id = 'mandateReinitiate';
	btn.dataset.toggle = "modal";
	btn.setAttribute("onclick", "getMandateDetails(this)");
	return btn;

}
function createChildDiv(customChildId) {
	let childDiv = document.createElement("div");
	childDiv.id = customChildId;
	return childDiv;
}

*/

function createWorkPage(response) {
	//$('#create').click(function() {

	var BBUJsonString = JSON.stringify(response);
	console.log(BBUJsonString);
	var myObj = JSON.parse(BBUJsonString);
	let bbuhtml = "";
	let subtxt_1 = "";
	let grandchildtxt_1 = ""
	var topval = 50;
	var allDivWidth = 160

	var leftVal = 35;
	var childLeftVal = leftVal + allDivWidth;
	var grandChildLeftVal = leftVal + allDivWidth;
	var gGrandChildLeftVal = leftVal + allDivWidth;
	var topRightVal = 50;
	//var sub_category_top = 37;


	//var targrt_sub_category_top = 38;
	var isLeafNode = true;
	for (let sides in myObj) {
		console.log("sides :: " + sides);
		if (sides == "left") {
			var secondArray = myObj[sides];
			for (let x in secondArray) {
				//console.log("x :: " + x + " :: " + secondArray[x].value);
				//sub_category_top = 37;
				var counter = 1;
				bbuhtml += "<div id='mybutton" + secondArray[x].value + x + "L' class='mybuttonL' draggable='true' ondragstart='dragged(event)' style='top:" + topval + "px;left:" + leftVal + "px;'>";

				var catgarray = secondArray[x].childList;


				var id = "hiddenContainerDiv_" + secondArray[x].value;
				if (catgarray != null && catgarray.length > 0) {
					isLeafNode = false;
				}

				//var parentChainId = ;
				bbuhtml +=
					"<Button id = 'handle_" + secondArray[x].value + "_L'  class = 'handleL parent '   value='" + secondArray[x].value +
					"' ondblclick='toggleVisibility(&#39" + id.trim() + x + "&#39)'></button><div id = 'label_" + secondArray[x].value +
					"_L' class='labell'><P id = 'desc" + secondArray[x].value + "L'> <B><I>" + secondArray[x].name + "</B></I>:" + secondArray[x].description + "</P>" +
					"<label id = 'qty" + secondArray[x].value + "Lb'>Qty.: </label> <span id = 'qty" + secondArray[x].value + "L'>" + secondArray[x].quantity
					+ "</span><label id = 'amt" + secondArray[x].value + "Lb'> Total ammount: Rs.</label> <span id = 'amt" + secondArray[x].value + "L'> " + secondArray[x].ammount
					+ "</span> <label id = 'rate" + secondArray[x].value + "Lb'> Rate:</label> <span id = 'rate" + secondArray[x].value + "L'>" + secondArray[x].rate + "</span></div>";

				///////////////////////////////////////////////NULL CHECK AFTER PARENT1//////////////////////////////////////////////////////////////////////////////
				if (!isLeafNode) {
					console.log("catgarray :: " + catgarray);



					let subtxt = "<div id= " + id + x + " style='display: none; position: absolute; top: 0px; left: 0px;  border: 1px solid blue;'>";
					//child

					var childTop = 90;
					for (let catg in catgarray) {
						console.log(">>>>>>>--- childTop " + childTop);

						var childIdL = secondArray[x].value + "_" + catgarray[catg].value;

						console.log("gChildId ", gChildId);

						var grandChildList = secondArray[x].childList[catg].grandChild;

						if (grandChildList != null && grandChildList.length > 0) {
							isLeafNode = false;
						}

						subtxt_1 = "<div id='myHiddenDiv_" + childIdL + "_" + x + "L' class='childHiddenDiv' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + childTop + "px;left:" + childLeftVal + "px;'>";
						subtxt_1 += "<Button id = 'handle_" + childIdL + "_L' ondblclick='toggleVisibility(&#39hiddenContainerDiv_" + childIdL + x + "&#39)' class = 'subhandleL' value='" + catgarray[catg].value + "'> </Button>"
							+ "<div class='labell' id = 'label_" + childIdL + "_L' ><p id='desc" + childIdL + "L'><b><i>" + catgarray[catg].name + "</b></i>:" + catgarray[catg].description
							+ " </p><label id = 'qty" + childIdL + "Lb'>Qty.: </label> <span id = 'qty" + childIdL + "L'>" + catgarray[catg].quantity + "</span><label id = 'amt" + childIdL + "Lb'> Total ammount: Rs.</label> <span id = 'amt" + childIdL + "L'> "
							+ catgarray[catg].ammount + "</span> <label id = 'rate" + childIdL + "lb'> Rate:</label> <span id = 'rate" + childIdL + "L'>" + catgarray[catg].rate + "</span></div>";



						///////////////////////////////////////////////NULL CHECK AFTER PARENT2//////////////////////////////////////////////////////////////////////////////

						if (!isLeafNode) {
							let grandchildtxt = "<div id = 'hiddenContainerDiv_" + childIdL + x + "' style='display: none; position: absolute; top: 0px; left: 0px;  border: 1px solid blue;'>";
							//grandChild
							var grandchild_top = 90;

							console.log("grandchild_top :: " + grandchild_top);
							for (let gchild in grandChildList) {
								var gChildId = secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value;
								var ggChildId;
								console.log("grand child-category name :: " + grandChildList[gchild].name);

								if (grandChildList[gchild] != null) {

									var gGrandChildList = secondArray[x].childList[catg].grandChild[gchild].gGrandchild;

									if (gGrandChildList != null && gGrandChildList.length > 0) {
										isLeafNode = false;
									}
								}

								grandchildtxt_1 = "<div id='HiddenDiv" + gChildId + "_" + x + "L' class='grandChildHiddenDivL' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + grandchild_top + "px;left:" + gGrandChildLeftVal + "px;'>";
								grandchildtxt_1 += "<Button id = 'handle" + gChildId + "_" + "L' class = 'subhandleL' ondblclick='toggleVisibility(&#39ggrandchild" + gChildId + x + "&#39)'> </Button>" +
									"<div class='labell' id = 'label" + gChildId + "_" + "L' ><p id='desc" + gChildId + "L'><b><i>" + grandChildList[gchild].name + "</b></i>: " + grandChildList[gchild].description
									+ "</p> <br><label id='qty" + gChildId + "Lb'> Qty.:  </label><span id='qty" + gChildId + "L'>" + grandChildList[gchild].quantity + "</span> <label id='amt" + gChildId + "Lb'> Total ammount: Rs. </label><span id = 'amt" + gChildId + "L'> "
									+ grandChildList[gchild].ammount + "</span><label id ='rate" + gChildId + "Lb' >Rate: </label><span id = 'rate" + gChildId + "L'>" + grandChildList[gchild].rate + "</span></div>";



								///////////////////////////////////////////////NULL CHECK AFTER PARENT3//////////////////////////////////////////////////////////////////////////////



								if (!isLeafNode) {

									let greatgrandchildtxt = "<div id= 'hiddenContainerDiv_" + gChildId + x + "' style='display: none; position: absolute; top: 0px; left: 0px;  border: 1px solid blue;'>";
									//greatgrandChild

									var ggrandchild_top = 95;
									for (let ggchild in gGrandChildList) {
										ggChildId = secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + gGrandChildList[ggchild].value;
										console.log("gGrandChildList index :: " + ggchild);
										let ggrandchildtxt = "<div id='HiddenDiv" + "_" + ggChildId + "_" + x + "L' class='gGrandChildHiddenDivL' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + ggrandchild_top + "px'>";
										ggrandchildtxt += "<Button id = 'handle" + "_" + ggChildId + "_" + "L' class = 'subhandleL' value='"
											+ gGrandChildList[ggchild].name + "'> </Button>" + "<div class='labell' id = 'label_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + gGrandChildList[ggchild].value + "_L' ><p><b><i>"
											+ gGrandChildList[ggchild].name + "</b></i>:" + gGrandChildList[ggchild].description
											+ " <br>Qty.: " + gGrandChildList[ggchild].quantity + " Total ammount: Rs.  " + gGrandChildList[ggchild].ammount
											+ " Rate: " + gGrandChildList[ggchild].rate + "</p></div>";
										ggrandchildtxt += "</div>";
										greatgrandchildtxt += ggrandchildtxt;
										ggrandchild_top = ggrandchild_top + 110;
										//sub_category_top = sub_category_top + 77;
									}
									greatgrandchildtxt += "</div>";
									grandchildtxt_1 += greatgrandchildtxt;

								}
								grandchild_top = grandchild_top + 110;
							}
							grandchildtxt_1 += "</div>";
							grandchildtxt += grandchildtxt_1;
							grandchildtxt += "</div>";
							subtxt_1 += grandchildtxt;
						}




					}
					subtxt_1 += "</div>";
					subtxt += subtxt_1;
					childTop = childTop + 110;

					//counter++;
				}

				subtxt += "</div>";
				bbuhtml += subtxt;



			}
			bbuhtml += "</div>";
			topval = topval + 120;
			counter = counter + 1;
		}
		else {
			var allRightDivHeight = 90;
			var grandChildTop;
			var gGgrandChildTop;
			var childTop;
			var allRightDivWidth = 160;
			var childLeft = -allRightDivWidth + 35;
			var grandChildLeft = -allRightDivWidth + 35;

			/*For right hand side */
			let textRight = "";
			var targetArray = myObj[sides];
			for (let target in targetArray) {

				console.log("target :: " + target + " :: " + targetArray[target].value);

				textRight += "<div id='mybutton_" + targetArray[target].value + "_R' class='mybuttonR' draggable='true' ondragstart='dragged(event)' style='top:" + topRightVal + "px;'>";

				var rightsubcategorry = targetArray[target].childList;

				var id = "hiddenContainerDiv_" + targetArray[target].value + "_R";
				textRight += "<Button id = 'parentHandle" + target + "R' class = 'handleR' value='" + targetArray[target].value + "' ondblclick='toggleVisibility(&#39" + id.trim() + "&#39)'></button><div id = 'parentLabel"
					+ target + "R' class='labelR'><p id='desc" + targetArray[target].value + "R'><b><i>" + targetArray[target].name + "</b></i>:" + targetArray[target].description
					+ "</p><label id = 'qty" + targetArray[target].value + "LbR'>Qty.: </label> <span id = 'qty" + targetArray[target].value + "R'> " + targetArray[target].quantity
					+ "</span> <label id = 'amt" + targetArray[target].value + "LbR'> Total ammount: Rs.</label> <span id = 'amt" + targetArray[target].value + "R'> "
					+ targetArray[target].ammount + "</span> <label id = 'rate" + targetArray[target].value + "LbR'> Rate:</label> <span id = 'rate" + targetArray[target].value + "R'>" + targetArray[target].rate + "</span></div>";


				grandChildTop = allRightDivHeight;
				if (rightsubcategorry != null && rightsubcategorry.length > 0) {
					let subtxtRight = "<div id='" + id + "' style='display: none; position: absolute; top: 0px; left: -80px;  border: 1px solid blue;'>";
					for (let catg in rightsubcategorry) {
						//var grandchildHiddenDivId = id + rightsubcategorry[catg].value;
						var rightGrandChild = rightsubcategorry[catg].grandChild
						var rightGrandChildId = targetArray[target].value + "_" + rightsubcategorry[catg].value

						console.log("sub-category index :: " + catg);

						let subtxtRight_1 = "<div id='myHiddenDiv" + rightGrandChildId
							+ "_R' class='myHiddenDivR' ondragstart='dragged(event)' style='top:" + grandChildTop + "px;left:" + childLeft + "px;'>";
						subtxtRight_1 += "<Button id = 'handle_" + rightGrandChildId
							+ "_R' class = 'handleR' ondblclick='toggleVisibility(&#39grandchildHiddenDiv_" + rightGrandChildId + "_R&#39)' value='"
							+ rightsubcategorry[catg].value + "'> </Button><div id = 'childLabel" + catg + "R' class='labelR'><p id='desc"
							+ rightGrandChildId + "R'><b><i>" + rightsubcategorry[catg].name + "</b></i>:"
							+ rightsubcategorry[catg].description
							+ " <br></p><label id = 'qty" + rightGrandChildId
							+ "LbR'>Qty.: </label> <span id = 'qty" + rightGrandChildId + "R'> "
							+ rightsubcategorry[catg].quantity
							+ "</span> <label id = 'amt" + rightGrandChildId
							+ "LbR'> Total ammount: Rs.</label> <span id = 'amt" + targetArray[target].value + "_"
							+ rightsubcategorry[catg].value + "R'> " + rightsubcategorry[catg].ammount
							+ " </span> <label id = 'rate" + rightGrandChildId
							+ "LbR'> Rate:</label> <span id = 'rate" + rightGrandChildId
							+ "R'> " + rightsubcategorry[catg].rate + "</span></div>";


						//grandChild
						if (rightGrandChild != null && rightGrandChild.length > 0) {
							let grandChildR = "<div id = 'grandchildHiddenDiv_" + rightGrandChildId + "_R' style='display: none; position: absolute; top: 0px; left: -80px;  border: 1px solid blue;'>";
							var grandchildR_top = allRightDivHeight;

							for (let rGrandChild in rightGrandChild) {
								var rightGtGrandChildId = rightGrandChildId + "_" + rightGrandChild[rGrandChild].value;

								let rGrandChild_1 = "<div id='HiddenDiv_" + rightGtGrandChildId + "_R' class='myHiddenDivR' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:"
									+ grandchildR_top + "px;left:" + grandChildLeft + "px;'>";
								rGrandChild_1 += "<Button id = 'handle_" + target + "." + rGrandChild + "_R' class = 'handleR' value='"
									+ rightGrandChild[rGrandChild].value + "'> </Button><div id = 'gchildlabel" + rGrandChild + "R' class='labelR'>" +
									"<p id='desc" + rightGtGrandChildId + "R'><b><i>" + rightGrandChild[rGrandChild].name + "</b></i>: " + rightGrandChild[rGrandChild].description
									+ "</p> <br><label id = 'qty" + rightGtGrandChildId + "LbR'>Qty.: </label> <span id = 'qty" + rightGtGrandChildId + "R'> " + rightsubcategorry[catg].quantity
									+ "</span> <label id = 'amt" + rightGtGrandChildId + "LbR'> Total ammount: Rs.</label> <span id = 'amt" + rightGtGrandChildId + "R'> " + rightsubcategorry[catg].ammount
									+ " </span> <label id = 'rate" + rightGtGrandChildId + "LbR'> Rate:</label> <span id = 'rate" + rightGtGrandChildId + "R'> " + rightsubcategorry[catg].rate + "</span></div>";
								rGrandChild_1 += "</div>";
								grandChildR += rGrandChild_1;
								grandchildR_top = grandchildR_top + 90;

							}
							grandChildR += "</div>";
							subtxtRight_1 += grandChildR;




						}
						subtxtRight_1 += "</div>";
						subtxtRight += subtxtRight_1;
						grandChildTop = grandChildTop + 105;
					}
					subtxtRight += "</div>";
					textRight += subtxtRight;
				}
				textRight += "</div>";
				topRightVal = topRightVal + 100;
			}
			bbuhtml += textRight;
		}
	}
	$('#masterDiv').append(bbuhtml);

}
