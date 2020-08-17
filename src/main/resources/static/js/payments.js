var addPayment = () => {
	$(".container-rows").prepend(getHtmlTemplate('paymentRowTemplate'));
	validateEmptyRows();
	setDeleteHandler();
};

var getPaymentsToSave = () => {
	const $rows = $(".container-rows .body-row.edit-item");
	let arrToSave = [];
	$rows.each((_i,o) => {
		let jItem = getInputValuesRow($(o));
		jItem.id = o.id;
		jItem.invoiceId = $('#invoiceId').val();
		jItem.userId = window.parent.$('#userId').val();
		arrToSave.push(jItem);
	});
	return arrToSave;
};

var validatePayments = () => {
	const $rows = $(".container-rows .body-row.edit-item");
	if($rows.length <= 0){
		alert("No hay cambios pendientes por guardar!");
		return false;
	}
	return validateRequiredDetails("Hay filas con valores incompletos. Completalas o eliminalas.");
};

function deletePayment(){
	const $row = $(this).closest(".payment-item"); 
	if(!$row.is("[id]")){
		$row.remove();
		return;
	}
	if(!confirm("Seguro quieres eliminar este abono de la factura?")){
		return;
	}	
	const id = $row.attr("id");
	$.post(`/payment/delete?paymentId=${id}`,
			null,
			function(jData){
				jData.afterOf = function(){
					$row.remove();
					if(window.parent.refreshTotalPayments)
						window.parent.refreshTotalPayments();
				};
				window.parent.ajaxResponse(jData);
			}
	);
}

var savePayments = () => {
	if(!validatePayments()){
		return;
	}
	const jParams = {
		jPayments: getPaymentsToSave()
	};
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/payment/save",
        data: JSON.stringify(jParams),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
        	data.afterOf = window.parent.refreshTotalPayments;
        	window.parent.ajaxResponse(data);
        },
        error: function (e) {
        	console.log(e);
        }
	});
};

var setDeleteHandler = () => {
	$(".body-row .icon-delete.icon-row-action")
	.unbind("click")
	.on("click", deletePayment);
};

$( document ).ready(function() {
   $(".main-container #newPayment").on("click", addPayment);
   $(".main-container #savePayments").on("click", savePayments);
   setDeleteHandler();
});