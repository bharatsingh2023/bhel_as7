/**
 * 
 */

$("document").ready(function() {
	var count = 1;
	var projectdata = ''
	var projectTable = $("#customerVendorMappingTable").DataTable();
	//projectDataTable.rows().remove().draw();

	function validateFileType(e) {

		var inputElement = e;
		console.log("inputElement " + inputElement.files);
		var files = e.files;
		var filename = files[0].name;


		var extension = filename.substr(filename.lastIndexOf("."));


		var allowedExtensionsRegx = /\.(xls|xlsx)(?:\?.*|)$/i;


		var isAllowed = allowedExtensionsRegx.test(extension);

		if (isAllowed) {
			//alert("File type is valid for the upload");
			return true;
		} else {
			//alert("Invalid File Type.");
			e.preventDefault();
			return false;
		}

	}

	$("#customerbtn").click(function(e) {

		e.preventDefault();
		let projectCode = $("#project_code_customer").val();
		let customerBBU = $('#cbbuFile')[0].files[0];
		let status = true;
		if (customerBBU != null) {
			// status = validateFileType(customerBBU);
			if (status) {
				var fd = new FormData();
				fd.append('CPC', projectCode);
				fd.append('PROJECT_NAME', $('#project_name_customer').val());
				//fd.append('CUSTOMER_CONTACT_NO', $('#project_ccn_customer').val());
				fd.append('cusBbuDate', $('#cusbbudate').val());
				fd.append('customerBBU', customerBBU);


				console.log("fd :: " + fd);


				var xhr = new XMLHttpRequest();
				xhr.open("POST", "/bhel/saveCustomerBbu");
				xhr.onreadystatechange = function() {
					//{"status":true,"message":"Saved successfully.","data":null}

					//var responseVal = JSON.stringify(success);
					if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
						let respText = xhr.responseText
						const resData = JSON.parse(respText);
						console.log(JSON.parse(respText));
						console.log("Status : ", resData.saveStatus)
						if (resData.saveStatus) {
							swal.fire({
								icon: 'success',
								title: 'Done',
								text: resData.saveMessage,
								confirmButtonText: 'Ok',
							}).then((result) => {
								/* Read more about isConfirmed, isDenied below */
								if (result.isConfirmed) {
									/*createVendorBbuFormAjax(projectCode)
									$('#project_code_vendor').val(projectCode);
									$("#customerData").modal('hide');*/
									location.reload();

								}
							})
						}
						else {
							swal.fire({
								icon: 'error',
								title: 'Failed',
								text: resData.saveMessage,
								confirmButtonText: 'Ok',
							}).then((result) => {
								/* Read more about isConfirmed, isDenied below */
								/*if (result.isConfirmed) {
									window.location.reload;
								}*/
							})
						}
					}
				};
				xhr.send(fd);
			} else {
				$("#customerbbuerr").html("<b>The selected file should be .xlsx or .xls</b>")
			}
		}
		else {
			$("#customerbbuerr").html("<b>Please provide a valid file</b>")
		}
	});

	/*EC upload*/
	$("#customerEcSavebtn").click(function(e) {

		e.preventDefault();
		let projectCode = $("#project_code_customer_ec").val();
		let customerEc = $('#cEcFile')[0].files[0]
		console.log("ec date :", $('#ecdate').val())
		if (customerEc != null) {
			var fd = new FormData();
			fd.append('CPC', projectCode);
			fd.append('PROJECT_NAME', $('#project_name_customer_ec').val());
			//fd.append('CUSTOMER_CONTACT_NO', $('#project_ccn_customer_ec').val());
			fd.append('ecDate', $('#ecdate').val());
			fd.append('customerEc', customerEc);


			console.log("fd :: " + fd);


			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/bhel/saveCustomerEc");
			xhr.onreadystatechange = function() {
				//{"status":true,"message":"Saved successfully.","data":null}

				//var responseVal = JSON.stringify(success);
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					let respText = xhr.responseText
					const resData = JSON.parse(respText);
					console.log(JSON.parse(respText));
					console.log("Status : ", resData.saveStatus)
					if (resData.saveStatus) {
						swal.fire({
							icon: 'success',
							title: 'Done',
							text: resData.saveMessage,
							confirmButtonText: 'Ok',
						}).then((result) => {
							/* Read more about isConfirmed, isDenied below */
							if (result.isConfirmed) {
								location.reload();

							}
						})
					}
					else {
						swal.fire({
							icon: 'error',
							title: 'Failed',
							text: resData.saveMessage,
							confirmButtonText: 'Ok',
						}).then((result) => {
							/* Read more about isConfirmed, isDenied below */
							/*if (result.isConfirmed) {
								window.location.reload;
							}*/
						})
					}
				}



			};
			xhr.send(fd);
		} else {
			$("#ecfileerr").html("<b>Please provide a valid file</b>")
		}
	});

	/*Vendor bbu upload*/
	$("#vendorbtn").click(function(e) {
		/*$("#vandor_name").val("");
		$("#workarea option").remove();
		$("#pkg option").remove();*/

		e.preventDefault();
		let projectCode = $("#project_code_vendor").val();
		let vendorBBU = $('#vendorbbufile')[0].files[0];
		if (vendorBBU != null) {
			var fd = new FormData();
			fd.append('CPC', projectCode);
			fd.append('PKG_ID', $('#pkg').val());
			fd.append('WORK_AREA', $('#workarea').val());
			fd.append('vendorName', $('#vandor_name').val());
			fd.append('vendorBBU', vendorBBU);
			fd.append('bbuDate', $('#vendorbbudate').val());
			//fd.append('PERIOD_FROM', $('#PERIOD_FROM').val());
			console.log("VAL", document.getElementById('pkg'))
			console.log("PKGVAL ",$('#pkg').val());
			console.log("WORKAREA", $('#workarea').val());
			console.log("VNAME ",$('#vandor_name').val())
			
			
			console.log("fd :: " + fd);


			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/bhel/saveVendorBBu");
			xhr.onreadystatechange = function() {
				//{"status":true,"message":"Saved successfully.","data":null}

				//var responseVal = JSON.stringify(success);
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					let respText = xhr.responseText
					const resData = JSON.parse(respText);
					console.log(JSON.parse(respText));
					console.log("Status : ", resData.saveStatus)
					if (resData.saveStatus) {
						swal.fire({
							icon: 'success',
							title: 'Done',
							text: resData.saveMessage,
							confirmButtonText: 'Ok',
						}).then((result) => {
							/* Read more about isConfirmed, isDenied below */
							if (result.isConfirmed) {
								location.reload();
							}
						})
					}
					else {
						swal.fire({
							icon: 'error',
							title: 'Failed',
							text: resData.saveMessage,
							confirmButtonText: 'Ok',
						}).then((result) => {

						})
					}
				}



			};
			xhr.send(fd);
		} else {
			$("#vendorbbufileerr").html("<b>Please provide a valid file</b>")
		}
	});
	/*Vendor wam upload*/
	$("#vendorWambtn").click(function(e) {



		e.preventDefault();
		let projectCode = $("#project_code_vendor_wam").val();
		let vendorWam = $('#vendorWamfile')[0].files[0];
		if (vendorWam != null) {
			var fd = new FormData();
			fd.append('CPC', projectCode);
			fd.append('PKG_ID', $('#pkgWam').val());
			fd.append('WORK_AREA', $('#workareaWam').val());
			fd.append('wamDate', $('#wamdate').val());
			fd.append('vendorName', $('#vandor_name').val());
			fd.append('vendorWam', vendorWam);

			console.log("fd :: " + fd);


			var xhr = new XMLHttpRequest();
			xhr.open("POST", "/bhel/saveVendorWam");
			xhr.onreadystatechange = function() {

				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					let respText = xhr.responseText
					const resData = JSON.parse(respText);
					console.log(JSON.parse(respText));
					console.log("Status : ", resData.saveStatus)
					if (resData.saveStatus) {
						swal.fire({
							icon: 'success',
							title: 'Done',
							text: resData.saveMessage,
							confirmButtonText: 'Ok',
						}).then((result) => {
							/* Read more about isConfirmed, isDenied below */
							if (result.isConfirmed) {
								location.reload();
							}
						})
					}
					else {
						swal.fire({
							icon: 'error',
							title: 'Failed',
							text: resData.saveMessage,
							confirmButtonText: 'Ok',
						}).then((result) => {

						})
					}
				}



			};
			xhr.send(fd);
		} else {
			$("#vendorbbuwamerr").html("<b>Please provide a valid file</b>")
		}
	});

	//open customer bbu modal///
	/*$(".customerDataModal").click(function() {
		$('#project_code_customer').val($(this).data('id'));
		$('#project_name_customer').val($(this).data('pname'));
		$('#project_ccn_customer').val($(this).data('ccn'));
		$("#customerData").modal('show');
	});*/


	function createVendorBbuFormAjax(projectCode, fromWam) {
		$.ajax({
			type: "POST",
			url: "/bhel/getWorkSpaceAndPackage",
			data: { "projectCode": projectCode },
			success: function(sdata) {
				console.log("success data -- >   :: ", sdata);
				var pkgArr = sdata.packages;
				var workAreaArr = sdata.workArea;
				var vendorArr = sdata.vendors;
				createVendorBbuForm(pkgArr, workAreaArr, vendorArr, fromWam)
				if (!fromWam) {
					$('#project_code_vendor').val(projectCode);
					$("#vendorData").modal('show');
				} else {
					$('#project_code_vendor_wam').val(projectCode);
					$("#vendorWam").modal('show');
				}


			}
		});
	}
function createVendorBbuForm(pkgArr, workAreaArr, vendorArr, fromWam) {
    let pkgOpt = '';
    let workAreaOpt = '';
    let vendorOpt = '';
    if (pkgArr != null && workAreaArr != null) {
        for (let package of pkgArr) {
            console.log("success data :: ", package["pKg"]);
            pkgOpt = document.createElement("option");
            pkgOpt.value = package["pKg"];
            pkgOpt.text = package["pKg"] + ' : ' + package["vendorName"];
            if (!fromWam) {
                $("#pkg").append(pkgOpt);
            } else {
                $("#pkgWam").append(pkgOpt);
            }
        }
        console.log("pkg Opt :: ", pkgOpt);

        for (let workArea of workAreaArr) {
            console.log("success data :: ", workArea);
            workAreaOpt = document.createElement("option");
            workAreaOpt.value = workArea;
            workAreaOpt.text = workArea;
            if (!fromWam) {
                $("#workarea").append(workAreaOpt);
            } else {
                $("#workareaWam").append(workAreaOpt);
            }
        }

        // Add event listener to the pkg select element
        $("#pkg").on("change", function() {
            // Get the selected package element
            let selectedPackage = this.options[this.selectedIndex];
            // Get the vendorName from the selected package
            let vendorName = selectedPackage.text.split(" : ")[1];
            // Set the value of #vendor_name field with the vendorName
            $("#vandor_name").prop("value", vendorName);
        });

        // Add event listener to the pkgWam select element
        $("#pkgWam").on("change", function() {
            // Get the selected package element
            let selectedPackage = this.options[this.selectedIndex];
            // Get the vendorName from the selected package
            let vendorName = selectedPackage.text.split(" : ")[1];
            // Set the value of #vendor_namewam field with the vendorName
            $("#vandor_name").prop("value", vendorName);
        });

        workAreaArr = [];
        pkgArr = [];
    }
}

	$("#vendorDataClose").click(function() {
		
		$("#pkg option").remove();
		$("#pkg").append("<option val=''>--Select--</option>");
		$("#workarea option").remove();
		$("#workarea").append("<option val=''>--Select--</option>");
		$("#vandor_name").val("");
		$('#vendorbbufile').val('');
		$('#vendorbbudate').val('');
		$("#vendorData").modal('hide');
		// document.getElementById('vendorbbufile').value = "";
		
	})
	$("#vendorWamClose").click(function() {
		$("#vendorWam").modal('hide');
		$("#pkg option").remove();
		$("#pkg").append("<option val=''>--Select--</option>");
		$("#workarea option").remove();
		$("#workarea").append("<option val=''>--Select--</option>");
		$("#vandor_name").val("");
		$('#vendorWamfile').val('');
	})

	document.addEventListener('click', function(e) {
		// Hamburger menu
		if (e.target.classList.contains('hamburger-toggle')) {
			e.target.children[0].classList.toggle('active');
		}
	});

	//let pkgDataTable = new DataTable('#PkgWorkAreaMappingTable');
	let pkgDataTable = '';

	/*$("#customerVendorMappingTable tbody tr td:not(:last-child)").click(function() {
		


	});*/
	//projectTable.on('click', 'tbody tr td:not(:last-child)', function() {
	projectTable.on('click', 'tbody td:not(:nth-child(5)):not(:nth-child(6)):not(:nth-child(7)):not(:nth-child(8))', function() {
		let count = 1;
		pkgDataTable = $("#PkgWorkAreaMappingTable").DataTable();
		projectdata = projectTable.row(this).data();
		console.log("project data : " + projectdata);
		pkgDataTable.clear().draw();
		createProjectDetailsView(projectdata[0]);
	});
	projectTable.on('click', 'tbody tr .customerDataModal', function() {
		console.log("bbu vtn click");
		$('#project_code_customer').val($(this).data('id'));
		$('#project_name_customer').val($(this).data('pname'));
		//$('#project_ccn_customer').val($(this).data('ccn'));
		$("#customerData").modal('show');

	});
	projectTable.on('click', 'tbody tr .vendorDataModal', function() {
		let projectCode = $(this).data('id');
		let fromWam = false;
		console.log("projectCode :: ", projectCode);
		createVendorBbuFormAjax(projectCode, fromWam);

	});
	projectTable.on('click', 'tbody tr .customerEcModal', function() {
		$('#project_code_customer_ec').val($(this).data('id'));
		$('#project_name_customer_ec').val($(this).data('pname'));
		//$('#project_ccn_customer_ec').val($(this).data('ccn'));
		$("#customerEc").modal('show');

	});
	projectTable.on('click', 'tbody tr .vendorWamModal', function() {
		let projectCode = $(this).data('id');
		//$("#vendorWam").modal('show');
		let fromWam = true;
		createVendorBbuFormAjax(projectCode, fromWam);

	});
	function createProjectDetailsView(projectCode) {
		$.ajax({
			type: "POST",
			url: "/bhel/getpackage",
			data: { "project_code": projectCode },
			success: function(sdata) {
				$("#pcode").html(projectdata[0]);
				$("#pname").html(projectdata[1]);
				$("#pvalue").html(projectdata[2]);
				$("#cname").html(projectdata[3]);
				$("#ccn").html(projectdata[4]);
				//$("#pkgs").val();
				console.log(" data workspace : " + JSON.stringify(sdata));
				/*$("#workAreaModalBtn").append("<button type='button' id='' class='btn btn-primary workAreaDataModalOp mb-2'" +
					"data-pkg='NA' data-pcode='" + projectdata[0] + "' >+ New Work Area</button>");*/
				$("#packageModalBtn button").remove();
				$("#packageModalBtn").append("<button type='button' id='' class='btn btn-primary packagesDataModalOp mb-2'" +
					"data-pcode='" + projectdata[0] + "' >+ New Package</button>");

				for (var j = 0, max = sdata.length; j < max; j++) {
					console.log(" data---> : " + sdata[j].workArea);
					if (sdata[j].workArea == null || sdata[j].workArea == "" || sdata[j].workArea == undefined) {
						$("#PkgWorkAreaMappingTable").dataTable().fnAddData(
							[sdata[j].vendorId, sdata[j].pKg, sdata[j].workArea, sdata[j].vendorName, "<button type='button' class='btn btn-primary workAreaDataModalOp'" +
								"data-pkg='" + sdata[j].pKg + "' data-pcode='" + projectdata[0] + "' data-vname='" + sdata[j].vendorName + "' data-vid='" + sdata[j].vendorId + "'>Add Work Area</button>"]);
					} else {
						$("#PkgWorkAreaMappingTable").dataTable().fnAddData(
							[sdata[j].vendorId, sdata[j].pKg, sdata[j].workArea, sdata[j].vendorName, " "]);
					}
					/*pkgDataTable.dataTable().fnAddData(
						[sdata[j].pKg, sdata[j].workArea, sdata[j].vendorName, "<button type='button' class='btn btn-primary workAreaDataModalOp'" +
							"data-pkg='" + sdata[j].pKg + "' data-pcode='" + projectdata[0] + "' >Add Work Area</button>"]);*/
					/*pkgDataTable.dataTable().fnAddData(
						[sdata[j].pKg, sdata[j].workArea, sdata[j].vendorName, "<button type='button' class='btn btn-primary workAreaDataModalOp'" +
							"data-pkg='" + sdata[j].pKg + "' data-pcode='" + projectdata[0] + "' >Add Work Area</button>"]);*/


					count++;


					createWorkAreaModal();

				}
				$("#projectView").modal('show');
				packageAreaModal();
			}
		});
	}
	$("#projectViewClose").click(function() {
		$("#packageModalBtn button").remove();
		pkgDataTable.destroy();
		$("#projectView").modal('hide');
	})

	function createWorkAreaModal() {
		//		$(document).ready(function() {
		/*$("body").on("click", ".workAreaDataModalOp", function() {
		console.log(" called >>>")
		let projectCode = $(this).data('pcode');
		let projectPkg = $(this).data('pkg');
		$.ajax({
			type: "GET",
			url: "/bhel/workArea",
			//data: { "projectCode": projectCode },
			success: function(sdata) {
				console.log("success data :: ", sdata);

				if (sdata != null) {
					console.log("Sdata :: ", sdata)
					for (let workArea of sdata) {
						console.log("success data :: ", workArea["work_AREA_NAME"]);
						let workAreaOpt = document.createElement("option");
						workAreaOpt.value = workArea["work_AREA_NAME"];
						workAreaOpt.text = workArea["work_AREA_NAME"];
						$("#work_area").append(workAreaOpt);
					}
				}
				$('#project_code_workarea').val(projectCode);
				$('#project_package_workarea').val(projectPkg);

				$("#workAreaAdd").modal('show');
				$("#packageModalBtn button").remove();
			}
		});

	});
	});
*/



		$(".workAreaDataModalOp").click(function() {
			console.log(" called >>>")
			$('#project_package_workarea').val("");

			let projectCode = $(this).data('pcode');
			let projectPkg = $(this).data('pkg');
			let vendorName = $(this).data('vname');
			let vendorId = $(this).data('vid');
			$.ajax({
				type: "GET",
				url: "/bhel/workArea",
				//data: { "projectCode": projectCode },
				success: function(sdata) {
					console.log("success data workarea:: ", sdata);
					$("#work_area").empty();
					/*let workAreaOptDef = document.createElement("option");
					workAreaOptDef.value = "";
					workAreaOptDef.text = "<--select workarea-->";
					$("#work_area").append(workAreaOptDef);*/
					if (sdata != null) {
						console.log("Sdata :: ", sdata);
						$("#work_area option").html("");
						for (let workArea of sdata) {

							console.log("success data :: ", workArea["work_AREA_NAME"]);
							let workAreaOpt = document.createElement("option");
							workAreaOpt.value = workArea["work_AREA_NAME"];
							workAreaOpt.text = workArea["work_AREA_NAME"];
							$("#work_area").append(workAreaOpt);
						}
					}
					$('#project_code_workarea').val(projectCode);
					$('#project_package_workarea').val(projectPkg);
					$('#project_vendor_workarea').val(vendorName);
					$('#project_vendor_id').val(vendorId);

					$('#workarea_name_error').html('');
					$('#work_area').val('');
					$("#workAreaAdd").modal('show');
					//$("#packageModalBtn button").remove();
				}
			});

		})
	}
	/*const btn1 = document.querySelector("workAreaDataModalOp1");
	console.log("btn1 - >> ", btn1);
	
	btn1.addEventListener('click', () => {
		console.log("HELLO!")
	})*/
	$("#workAreaSavebtn").click(function() {
		var workarea = $("#work_area").val();

		var projectCode = $("#project_code_workarea").val();
		var vendorName = $('#project_vendor_workarea').val();

		if (workarea == undefined || workarea == null || workarea == '') {
			$('#workarea_name_error').html('<b>** Please select a workarea</b>');
		}

		else {
			$('#workarea_name_error').html('');
			var workAreadata = {
				"projectCode": projectCode,
				"projectPackage": $("#project_package_workarea").val(),
				"projectWorkArea": workarea.toString(),
				"vendorName": vendorName.toString(),
				"vendorId": $("#project_vendor_id").val()
			}
			console.log("work area :: " + $("#work_area").val());
			$.ajax({
				type: "POST",
				url: "/bhel/newWorkArea",
				data: workAreadata,
				success: function(sdata) {
					console.log("success data :: ", sdata);
					if (sdata.saveStatus) {
						swal.fire({
							icon: 'success',
							title: 'Done',
							text: sdata.saveMessage,
							confirmButtonText: 'Ok',
						}).then((result) => {

							/* Read more about isConfirmed, isDenied below */
							if (result.isConfirmed) {


								$("#workAreaModalBtn button").remove();
								console.log("pkg DataTable ::", pkgDataTable);
								pkgDataTable.destroy();
								pkgDataTable = $("#PkgWorkAreaMappingTable").DataTable();
								pkgDataTable.clear().draw();

								createProjectDetailsView(projectCode);
								$("#packageModalBtn button").remove();
								$("#workAreaAdd").modal('hide');
								//$("#projectView").modal('hide');

							}
						})
					}
				}
			});
		}
	})

	function packageAreaModal() {
		$(".packagesDataModalOp").click(function() {

			console.log("running >>>");

			let projectCode = $(this).data('pcode');
			console.log("project code-------->", projectCode)
			$('#project_code_workarea1').val(projectCode);

			$('#package_name_error').html('');
			$('#project_code_packages').val('');
			$("#packageAdd").modal('show');

			// Unbind previous click event handler before adding a new one
			/*$("#packageAdd").modal('show');$("#packagesubmitbutton").off('click').click(function(event) {
				event.preventDefault();
			
			});*/

		});
	}

	$("#packagesubmitbutton").click(function(event) {
		event.preventDefault();
		// Get form data
		let PACKAGE_NAME = $("#project_code_packages").val();
		console.log("pkg value----->", PACKAGE_NAME);
		if (PACKAGE_NAME == undefined || PACKAGE_NAME == null || PACKAGE_NAME == '') {
			$('#package_name_error').html('<b>** Please enter a valid package name</b>');
		}

		else {
			$('#package_name_error').html('');

			let packageData = {

				PACKAGE_NAME: PACKAGE_NAME,
				VendorName: $("#project_code_vendor1").val(),
				projectCode: $('#project_code_workarea1').val()
			};

			console.log("Package data :: ", packageData);

			$.ajax({
				type: "POST",
				url: "/bhel/saveAddPackage",
				data: packageData,
				success: function(response) {
					console.log("Package added successfully!", response);
					//const {package_NAME, vendor_NAME} = respones;
					if (response) {
						// Success message
						swal.fire({
							icon: 'success',
							title: 'Done',
							text: "Package added successfully!",
							confirmButtonText: 'Ok',
						}).then((result) => {
							console.log("RESULT -- >> ", result)
							if (result.isConfirmed) {
								// Clear the form fields after successful submission
								$("#project_code_packages").val('');
								$("#project_code_vendor1").val('');

								$("#packageAdd").modal('hide');
								$("#workAreaModalBtn button").remove();
								$(".packagesDataModalOp").remove();
								console.log("pkg DataTable ::", pkgDataTable);
								pkgDataTable.destroy();
								pkgDataTable = $("#PkgWorkAreaMappingTable").DataTable();
								pkgDataTable.clear().draw();
								createProjectDetailsView($('#project_code_workarea1').val());

							}
						});
					} else {

						swal.fire({
							icon: 'error',
							title: 'Error',
							text: "Failed to add package. Please try again later.",
							confirmButtonText: 'Ok',
						});
					}
				},
				error: function(xhr, status, error) {
					console.error("Error adding package:", error);
					// Handle AJAX error
					swal.fire({
						icon: 'error',
						title: 'Error',
						text: "An error occurred while adding the package. Please try again later.",
						confirmButtonText: 'Ok',
					});
				}
			});
		}
	});


});





