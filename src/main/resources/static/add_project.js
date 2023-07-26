var input = '';
var display = '';

function fetchproject() {
	input = document.getElementById("projectId").value;
	console.log(input);
	display = document.getElementById("projectDisplay");
	var request = new XMLHttpRequest();
	request.open("GET", "/bhel/getCPC_Master?search=" + input);
	request.onreadystatechange = function() {
		if (request.readyState == 4 && request.status == 200) {
			var response = JSON.parse(request.responseText);
			display.innerHTML = "";
			const keys = Object.keys(response);
			for (let i = 0; i < keys.length; i++) {
				const key = keys[i];
				console.log("response val -> ", response[key]);
				var resultElement = document.createElement("div");
				resultElement.classList.add("suggestion-list");
				resultElement.innerText = `${response[key].customer_PROJECT_CODE} : ${response[key].customer_PROJECT_NAME}`;


				//event listener for each result to select
				resultElement.addEventListener("click", function() {
					selectOption(resultElement.innerText);
				});

				display.appendChild(resultElement);
			}
		}
	};

	request.send();
}

function selectOption(option) {
	var cpc = option.split(":");
	console.log("cpc name", cpc[1]);
	document.getElementById("projectId").value = cpc[1];
	document.getElementById("projectDisplay").innerHTML = "";
	//calling ccn based on selected project
	getCCN(option);
}

document.addEventListener("click", function(event) {
	var targetElement = event.target;

	if (targetElement != display && targetElement != document.getElementById("projectId")) {
		display.innerHTML = ""; // Clear the display
	}
});


function getCCN(option) {
	console.log("form getCCN", option);
	var cpc = option.split(":", 1);
	console.log(cpc[0]);
	var ccnSelect = document.getElementById("ccnId");
	var requestCCN = new XMLHttpRequest();
	requestCCN.open("GET", "/bhel/getccn?cpc=" + cpc[0]);
	requestCCN.onreadystatechange = function() {
		console.log("executing..ccn");
		if (requestCCN.readyState == 4 && requestCCN.status == 200) {
			var responseCCN = JSON.parse(requestCCN.responseText);
			console.log(responseCCN);
			if (Object.keys(responseCCN).length !== 0) {
				ccnSelect.innerHTML = "";
				const keys = Object.keys(responseCCN);
				for (let i = 0; i < keys.length; i++) {
					const key = keys[i];
					console.log("CCN Values ", responseCCN[key]);
					var option = document.createElement("option");
					option.setAttribute("value", key);
					let optionText = document.createTextNode(responseCCN[key]);
					option.appendChild(optionText);
					ccnSelect.appendChild(option);

					option.addEventListener("click", function() {
						alert(option.value);
					});
				}
			} else {
				ccnSelect.innerHTML = "<option value=''>Not Available!</option>";
			}
		}
	};
	requestCCN.send();
}


