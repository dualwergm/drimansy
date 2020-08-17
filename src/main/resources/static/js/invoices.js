var addInvoice = () => {
	$("#workspaceTitle>span").text("Nueva factura");
	const jparamsStr = encodeURIComponent(JSON.stringify({}));
	$("#workspaceContainer").load(`/invoice/create`);
};

function refreshTotalPayments(){
	$("#workspaceContainer").load('/invoice/list');
}

var gotoEdit = function (obj){
	$("#workspaceTitle>span").text("Editar factura");
	const $rowItem = $(obj).closest('div.body-row');
	const id = parseInt($rowItem.attr("id"));
	$("#workspaceContainer").load(`/invoice/edit/${id}`);
};

function showPayments(){
	const id = $(this).closest(".invoice-item").attr("id");
	var paymentModal = new Modal({title: "Abonos", 
		ifUrl: `/payment/list?invoiceId=${id}&isModal=1`,
		ifId: 'paymentsModal'
	}) 
	paymentModal.showIFrameModal();
};

$( document ).ready(function() {
   $(".main-container #newInvoice").on("click", addInvoice);
   $(".icon-circle-money.icon-row-action").on("click", showPayments);
   $("div.result-table a.go-to").on("click", function(){gotoEdit(this, 0)});
   $("span.icon-assignment_return.icon-row-action").on("click", function(){gotoEdit(this, 1)});   
});