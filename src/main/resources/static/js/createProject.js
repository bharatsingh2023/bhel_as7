var ccnDisplay = '';
function fetchCcn() {
  var inputCcn = document.getElementById("ccnId").value;
  console.log(inputCcn);
  ccnDisplay = document.getElementById("ccnDisplay");
  var requestCCN = new XMLHttpRequest();
  requestCCN.open("GET", "/bhel/getccn?search=" + inputCcn);
  requestCCN.onreadystatechange = function() {
    console.log("executing..ccn");
    if (requestCCN.readyState == 4 && requestCCN.status == 200) {
      var responseCCN = JSON.parse(requestCCN.responseText);
      console.log(responseCCN);
      ccnDisplay.innerHTML = "";
      const keys = Object.keys(responseCCN);
      for (let i = 0; i < keys.length; i++) {
        const key = keys[i];
        console.log("responseccn val -> ", responseCCN[key]);
        var resultElement = document.createElement("div");
        resultElement.classList.add("suggestion-list");
        resultElement.innerText = `${responseCCN[key].customer_CONTACT_NO} : ${responseCCN[key].customet_NAME}`;
        
        
        (function(result) {
          result.addEventListener("click", function(event) {
            selectCcn(event.target.innerText);
          });
        })(resultElement);

        ccnDisplay.appendChild(resultElement);
      }
    }
  };
  requestCCN.send();
}

function selectCcn(option) {
	var ccn=option.split(":");
  var ccnInput = document.getElementById("ccnId");
  ccnInput.value = ccn[0].trim();
  ccnInput.focus();
  ccnDisplay.innerHTML = "";
}


document.addEventListener("click", function(event) {
  var targetElement = event.target;

  if (!ccnDisplay.contains(targetElement) && targetElement !== document.getElementById("ccnId")) {
    ccnDisplay.innerHTML = "";
  }
});

