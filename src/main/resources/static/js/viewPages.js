var display = '';
function fetchproject() {
	var input = document.getElementById("projectId").value;
	console.log(input);
	display = document.getElementById("projectDisplay");
	var request = new XMLHttpRequest();
	request.open("GET", "/bhel/getCpc?search=" + input);
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = JSON.parse(request.responseText);
			console.log("RES :: "+response);
			display.innerHTML = "";
			const keys = Object.keys(response);
			for (let i = 0; i < keys.length; i++) {
				const key = keys[i];
				console.log("response val -> ", response[key]);
				var resultElement = document.createElement("div");
				resultElement.classList.add("suggestion-list");
				resultElement.innerText = `${response[key].customer_PROJECT_CODE} : ${response[key].customer_PROJECT_NAME}`;

				(function(result) {
					// Event listener for each result to select
					result.addEventListener("click", function() {
						var selectElement = document.getElementById("packageId");
						selectElement.innerHTML = "";
						selectOption(result.innerText);
					});
				})(resultElement);

				display.appendChild(resultElement);
			}
		}
	};

	request.send();
}



function selectOption(option) {
	var cpc = option.split(":");
	console.log("cpc name", cpc[1]);
	var projectIdInput = document.getElementById("projectId");
	projectIdInput.value = cpc[0].trim();
	//projectIdInput.value = option;
	projectIdInput.focus();
	FetchCcn(cpc[0].trim());
	FetchPackages(cpc[0].trim())
	document.getElementById("projectDisplay").innerHTML = "";
}

document.addEventListener("click", function(event) {
	var targetElement = event.target;

	if (targetElement != display && targetElement != document.getElementById("projectId")) {
		display.innerHTML = ""; // Clear the display
	}
});



function FetchCcn(cpc) {
	//var field = document.getElementById("customerId");
	var field=document.getElementsByClassName("customerName");
	var requestCCN = new XMLHttpRequest();
	requestCCN.open("GET", "/bhel/getCcnByCpc?search=" + cpc);
	requestCCN.onreadystatechange = function() {
		if (requestCCN.readyState == 4 && requestCCN.status == 200) {
			var responseCCN = JSON.parse(requestCCN.responseText);
			console.log(responseCCN);
			//var field = document.getElementById("customerId");
			for(var i=0;i<field.length;i++){
				field[i].value = responseCCN.customer_CONTACT_NO;
			}
			
		}
	};
	requestCCN.send();
}


function FetchPackages(cpc) {
	console.log("packages executing", cpc);
	var requestPkg = new XMLHttpRequest();
	requestPkg.open("GET", "/bhel/fetchPackages?search=" + cpc);
	requestPkg.onreadystatechange = function() {
		console.log("executing..ccn");
		if (requestPkg.readyState == 4 && requestPkg.status == 200) {
			var responsePkg = JSON.parse(requestPkg.responseText);
			console.log("RESPONSE PKG--------->", responsePkg);
			if (responsePkg !== null && responsePkg.length > 0 && containsNonNullValues(responsePkg)) {
				var selectElement = document.getElementById("packageId");
				selectElement.innerHTML = "";

				var defaultOption = document.createElement("option");
				defaultOption.value = "";
				defaultOption.text = "--select package--";
				selectElement.appendChild(defaultOption);

				for (var i = 0; i < responsePkg.length; i++) {
					var package_NAME = responsePkg[i].package_NAME;
					if (package_NAME !== null) {
						var option = document.createElement("option");
						console.log("rpn ",responsePkg[i]);
						option.value = responsePkg[i].package_NAME;
						option.text = package_NAME+' : '+responsePkg[i].vendor_NAME;
						option.id = "option" + i;
						selectElement.appendChild(option);
					}
				}

				selectElement.addEventListener("click", function(e) {
					console.log("etv -> ",e.target.value);
					var selectedOption = selectElement.options[selectElement.selectedIndex];
					var selectedValue = selectedOption.value;
					console.log("selected value is", selectedValue);

					FetchWorkArea(selectedValue);
				});
			} else {
				if (responsePkg === null || (responsePkg.length > 0 && !containsNonNullValues(responsePkg))) {
					FetchWorkAreaByCpc(cpc);

				}
			}
		}
	};
	requestPkg.send();
}

function containsNonNullValues(arr) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i].package_NAME !== null) {
			return true;
		}
	}
	return false;
}


function FetchWorkArea(selectedValue) {
	console.log("------------->>",selectedValue);
	var requestWork = new XMLHttpRequest();
	requestWork.open("GET", "/bhel/fetchWorkArea?search=" + selectedValue.trim());
	requestWork.onreadystatechange = function() {
		if (requestWork.readyState == 4 && requestWork.status == 200) {
			var responseWork = JSON.parse(requestWork.responseText);
			console.log(responseWork);
			var selectElement = document.getElementById("workAreaId");
			selectElement.innerHTML = "";

			// Creating the default "--select--" option
			var defaultOption = document.createElement("option");
			defaultOption.value = "";
			defaultOption.text = "--Select Work_Area--";
			selectElement.appendChild(defaultOption);

			for (var i = 0; i < responseWork.length; i++) {
				var option = document.createElement("option");
				option.value = responseWork[i].work_AREA_NAME.trim();
				option.text = responseWork[i].work_AREA_NAME.trim();
				option.id = "option" + i;
				selectElement.appendChild(option);
			}
		}
	};
	requestWork.send();
}


function FetchWorkAreaByCpc(cpc) {

	console.log("FetchWorkAreaByCpc", cpc);
	var requestWork = new XMLHttpRequest();
	requestWork.open("GET", "/bhel/fetchWorkArea?search=" + cpc.trim());
	requestWork.onreadystatechange = function() {
		if (requestWork.readyState == 4 && requestWork.status == 200) {
			var responseWork = JSON.parse(requestWork.responseText);
			var selectElement = document.getElementById("workAreaId");
			selectElement.innerHTML = "";

			// Creating the default "--select--" option
			var defaultOption = document.createElement("option");
			defaultOption.value = "";
			defaultOption.text = "--Select Work_Area--";
			selectElement.appendChild(defaultOption);

			for (var i = 0; i < responseWork.length; i++) {
				var option = document.createElement("option");
				option.value = responseWork[i].work_AREA_NAME;
				option.text = responseWork[i].work_AREA_NAME;
				option.id = "option" + i;
				selectElement.appendChild(option);
			}
		}
	};
	requestWork.send();


};



