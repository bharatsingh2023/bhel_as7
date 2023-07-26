function createWorkPage(response) {
	//alert("done");
	var BBUJsonString = JSON.stringify(response);
	console.log(BBUJsonString);
	var myObj = JSON.parse(BBUJsonString);
	console.log("myObj :: " + myObj);
	let bbuhtml = "";
	var topval = 50;
	var allDivWidth = 160

	var leftVal = 35;
	var childLeftVal = leftVal + allDivWidth;
	var grandChildLeftVal = leftVal + allDivWidth;
	var gGrandChildLeftVal = leftVal + allDivWidth;
	var topRightVal = 50;
	//var sub_category_top = 37;


	//var targrt_sub_category_top = 38;
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


				if (catgarray != null && catgarray.length > 0) {
					console.log("catgarray :: " + catgarray);
					var id = "hiddenContainerDiv_" + secondArray[x].value;


					//var parentChainId = ;
					bbuhtml +=
						"<Button id = 'handle_" + secondArray[x].value + "_L' class = 'handleL parent' value='" + secondArray[x].value +
						"' ondblclick='toggleVisibility(&#39" + id.trim() + x + "&#39)'></button><div id = 'label_" + secondArray[x].value +
						"_L' class='labell'><P> <B><I>" + secondArray[x].name + "</B></I>:" + secondArray[x].description + "</P></div>";

					bbuhtml += "<div id='parentValue_" + secondArray[x].value + "'>Qty.: Value: Rate: </div>"


					let subtxt = "<div id= " + id + x + " style='display: none; position: absolute; top: 0px; left: 0px;  border: 1px solid blue;'>";
					//child

					var childTop = 90;
					for (let catg in catgarray) {


						console.log(">>>>>>>--- childTop " + childTop);

						var gChildId = "hiddenContainerDiv_" + secondArray[x].value + "_" + catgarray[catg].value;
						var grandChildList = secondArray[x].childList[catg].grandChild;

						if (grandChildList != null && grandChildList.length > 0) {
							//console.log("grandChildList index :: " + grandChildList);
							let subtxt_1 = "<div id='myHiddenDiv" + secondArray[x].value + "_" + catgarray[catg].value + "_L' class='childHiddenDiv' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + childTop + "px;left:" + childLeftVal + "px;'>";
							subtxt_1 += "<Button id = 'handle_" + secondArray[x].value + "_" + catgarray[catg].value + "_L' ondblclick='toggleVisibility(&#39" + gChildId + x + "&#39)' class = 'subhandleL' value='" + catgarray[catg].value + "'> </Button>" +
								"<div class='labell' id = 'label_" + secondArray[x].value + "_" + catgarray[catg].value + "_L' ><p><b><i>" + catgarray[catg].name + "</b></i>:" + catgarray[catg].description + "</p></div>";
							subtxt_1 += "<div id='childValue_" + secondArray[x].value + "'>Qty.: Value: Rate: </div>";
							//childTop_prev = childTop;

							//top_val_prev = top_val_prev + 60;

							let grandchildtxt = "<div id = " + gChildId + x + " style='display: none; position: absolute; top: 0px; left: 0px;  border: 1px solid blue;'>";
							//grandChild
							var grandchild_top = 90;

							console.log("grandchild_top :: " + grandchild_top);
							for (let gchild in grandChildList) {
								var ggChildId = "hiddenContainerDiv_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value;

								if (grandChildList[gchild] != null) {

									var gGrandChildList = secondArray[x].childList[catg].grandChild[gchild].gGrandchild;

									if (gGrandChildList != null && gGrandChildList.length > 0) {

										console.log("grand child-category name :: " + grandChildList[gchild].name);

										let grandchildtxt_1 = "<div id='HiddenDiv" + "_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + "L' class='grandChildHiddenDivL' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + grandchild_top + "px;left:" + gGrandChildLeftVal + "px;'>";
										grandchildtxt_1 += "<Button id = 'handle" + "_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + "L' class = 'subhandleL' ondblclick='toggleVisibility(&#39ggrandchild" + ggChildId + x + "&#39)'> </Button>" +
											"<div class='labell' id = 'label" + "_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + "L' ><p><b><i>" + grandChildList[gchild].name + "</b></i>:" + grandChildList[gchild].description + "</p></div>";
										grandchildtxt_1 += "<div id='grandValue_"+secondArray[x].value+"_" + catgarray[catg].value + "_" + grandChildList[gchild].value +"'>Qty.: Value: Rate: </div>"

										let greatgrandchildtxt = "<div id= " + ggChildId + x + " style='display: none; position: absolute; top: 0px; left: 0px;  border: 1px solid blue;'>";
										//greatgrandChild

										var ggrandchild_top = 95;
										for (let ggchild in gGrandChildList) {
											console.log("gGrandChildList index :: " + ggchild);
											let ggrandchildtxt = "<div id='HiddenDiv" + "_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + gGrandChildList[ggchild].value + "_" + "L' class='gGrandChildHiddenDivL' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + ggrandchild_top + "px'>";
											ggrandchildtxt += "<Button id = 'handle" + "_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + gGrandChildList[ggchild].value + "_" + "L' class = 'subhandleL' value='" + gGrandChildList[ggchild].name + "'> </Button>" +
												"<div class='labell' id = 'label_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_" + gGrandChildList[ggchild].value + "_L' ><p><b><i>" + gGrandChildList[ggchild].name + "</b></i>:" + gGrandChildList[ggchild].description + "</p></div>";
											grandchildtxt_1 += "<div id='gGrandValue_"+secondArray[x].value+"_" + catgarray[catg].value + "_" + grandChildList[gchild].value +"_" + gGrandChildList[ggchild].value +"'>Qty.: Value: Rate: </div>"
											ggrandchildtxt += "</div>";
											greatgrandchildtxt += ggrandchildtxt;
											ggrandchild_top = ggrandchild_top + 110;
											//sub_category_top = sub_category_top + 77;
										}
										greatgrandchildtxt += "</div>";
										grandchildtxt_1 += greatgrandchildtxt;
										grandchildtxt_1 += "</div>";
										grandchildtxt += grandchildtxt_1;




									} else {
										console.log("grand child-category name :: " + grandChildList[gchild].name);
										let grandchildtxt_1 = "<div id='HiddenDiv_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_L' class='grandChildHiddenDivL' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + grandchild_top + "px;left:" + grandChildLeftVal + "px;'>";
										grandchildtxt_1 += "<Button id = 'handle_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_L' class = 'subhandleL' value='" + grandChildList[gchild].value + "' ondblclick='toggleVisibility(&#39ggrandchild" + ggChildId + "&#39)'> </Button>" +
											"<div class='labell' id = 'label_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + grandChildList[gchild].value + "_L' ><p><b><i>" + grandChildList[gchild].name + "</b></i>:" + catgarray[catg].description + "</p></div>";
										grandchildtxt_1 += "</div>";
										grandchildtxt += grandchildtxt_1;
										//grandchild_top_prev = grandchild_top
										//grandchild_top = grandchild_top + 5;
									}
									grandchild_top = grandchild_top + 110;
								}
							}
							grandchildtxt += "</div>";
							subtxt_1 += grandchildtxt;
							subtxt_1 += "</div>";
							subtxt += subtxt_1;


						} else {
							console.log("sub-category index :: " + catgarray[catg]);
							let subtxt_1 = "<div id='myHiddenDiv_" + secondArray[x].value + "_" + catgarray[catg].value + "_" + "L' class='childHiddenDiv' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + childTop + "px;left:" + childLeftVal + "px;'>";
							subtxt_1 += "<Button id = 'handle_" + secondArray[x].value + "_" + catgarray[catg].value + "_L' ondblclick='toggleVisibility(&#39" + gChildId + "&#39)' class = 'subhandleL' value='" + catgarray[catg].value + "'> </Button>" +
								"<div class='labell' id = 'label_" + secondArray[x].value + "_" + catgarray[catg].value + "_L' ><p><b><i>" + catgarray[catg].name + "</b></i>:" + catgarray[catg].description + "</p></div>";
							subtxt_1 += "</div>";
							subtxt += subtxt_1;
							//childTop_prev = childTop;
							//childTop = childTop + 95;

						}
						childTop = childTop + 110;
						//counter++;
					}
					subtxt += "</div>";
					bbuhtml += subtxt;



				} else {
					bbuhtml += "<Button id = 'handle" + secondArray[x].value + "L' class = 'handleL' value='" + secondArray[x].value + "'></button><div id = 'parentlabel" + x + "L' class='labell'><p><b><i>" + secondArray[x].name + "</b></i>:" + secondArray[x].description + "</p></div>";
				}



				bbuhtml += "</div>";
				topval = topval + 120;
				counter = counter + 1;
			}
		} else {
			var allRightDivHeight = 100;
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
				if (rightsubcategorry != null && rightsubcategorry.length > 0) {
					var id = "hiddenContainerDiv_" + targetArray[target].value + "_R";
					textRight += "<Button id = 'parentHandle" + target + "R' class = 'handleR' value='" + targetArray[target].value + "' ondblclick='toggleVisibility(&#39" + id.trim() + "&#39)'></button><div id = 'parentLabel" + target + "R' class='labelR'><p><b><i>" + targetArray[target].name + "</b></i>:" + targetArray[target].description + "</p></div>";

					let subtxtRight = "<div id='" + id + "' style='display: none; position: absolute; top: 0px; left: -80px;  border: 1px solid blue;'>";
					grandChildTop = allRightDivHeight;

					for (let catg in rightsubcategorry) {
						var grandchildHiddenDivId = id + rightsubcategorry[catg].value;
						var rightGrandChild = rightsubcategorry[catg].grandChild
						if (rightGrandChild != null && rightGrandChild.length > 0) {
							console.log("sub-category index :: " + catg);
							let subtxtRight_1 = "<div id='myHiddenDiv" + targetArray[target].value + "_" + rightsubcategorry[catg].value + "_R' class='myHiddenDivR' ondragstart='dragged(event)' style='top:" + grandChildTop + "px;left:" + childLeft + "px;'>";
							subtxtRight_1 += "<Button id = 'handle_" + targetArray[target].value + "_" + rightsubcategorry[catg].value + "_R' class = 'handleR' ondblclick='toggleVisibility(&#39grandchildHiddenDiv" + catg + rightsubcategorry[catg].value + "R&#39)' value='" + rightsubcategorry[catg].value + "'> </Button><div id = 'childLabel" + catg + "R' ><p><b><i>" + rightsubcategorry[catg].name + "</b></i>:" + rightsubcategorry[catg].description + "</p></div>";
							subtxtRight_1 += "<div id='childValue_"+targetArray[target].value + "_" + rightsubcategorry[catg].value +"_R'>Qty.: Value: Rate: </div>"
							let grandChildR = "<div id = 'HiddenDiv_" + grandchildHiddenDivId + "_R' style='display: none; position: absolute; top: 0px; left: -80px;  border: 1px solid blue;'>";
							//grandChild
							var grandchildR_top = allRightDivHeight;
							for (let rGrandChild in rightGrandChild) {
								let rGrandChild_1 = "<div id='HiddenDiv_" + targetArray[target].value + "_" + rightsubcategorry[catg].value + "_" + rightGrandChild[rGrandChild].value + "_R' class='myHiddenDivR' ondrop='drop(event)' ondragover='allowDrop(event)' style='top:" + grandchildR_top + "px;left:" + grandChildLeft + "px;'>";
								rGrandChild_1 += "<Button id = 'handle_" + target + "." + rGrandChild + "_R' class = 'handleR' value='" + rightGrandChild[rGrandChild].value + "'> </Button><div id = 'gchildlabel" + rGrandChild + "R' ><p><b><i>" + rightGrandChild[rGrandChild].name + "</b></i>:" + rightGrandChild[rGrandChild].description + "</p></div>";
								rGrandChild_1 += "</div>";
								grandChildR += rGrandChild_1;
								grandchildR_top = grandchildR_top + 90;

							}
							grandChildR += "</div>";
							subtxtRight_1 += grandChildR;
							subtxtRight_1 += "</div>";
							subtxtRight += subtxtRight_1;



						} else {
							console.log("sub-category index :: " + catg);
							let subtxtRight_1 = "<div id='myHiddenDiv" + targetArray[target].value + "_" + rightsubcategorry[catg].value + "_R' class='myHiddenDivR' ondragstart='dragged(event)' style='top:" + grandChildTop + "px;left:" + childLeft + "px;'>";
							subtxtRight_1 += "<Button id = 'handle_" + targetArray[target].value + "_" + rightsubcategorry[catg].value + "_R' class = 'handleR' ondblclick='toggleVisibility(&#39grandchildHiddenDiv" + catg + rightsubcategorry[catg].value + "R&#39)' value='" + rightsubcategorry[catg].value + "'> </Button><div id = 'childLabel" + catg + "R' ><p><b><i>" + rightsubcategorry[catg].name + "</b></i>:" + rightsubcategorry[catg].description + "</p></div>";
							subtxtRight_1 += "</div>";
							subtxtRight += subtxtRight_1;


						}
						grandChildTop = grandChildTop + 105;
					}
					subtxtRight += "</div>";
					textRight += subtxtRight;
				} else {
					textRight += "<Button id = 'Handle_" + targetArray[target].value + "_R' class = 'handleR' value='" + targetArray[target].value + "'></button><div id = 'Label_" + targetArray[target].value + "_R' class='labelR'><p><b><i>" + targetArray[target].name + "</b></i>:" + targetArray[target].description + "</p></div>";
				}
				textRight += "</div>";
				topRightVal = topRightVal + 100;
			}
			bbuhtml += textRight;
		}
	}
	$('#masterDiv').append(bbuhtml);
}

