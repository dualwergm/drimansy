var addInvoice = () => {
	$("#workspaceTitle>span").text("Nueva factura");
	const jparamsStr = encodeURIComponent(JSON.stringify({}));
	$("#workspaceContainer").load(`/invoice/create`);
};

var gotoEdit = function (obj, arrive){
	$("#workspaceTitle>span").text("Editar factura");
	const $rowItem = $(obj).closest('div.body-row');
	const id = parseInt($rowItem.attr("id"));
	$("#workspaceContainer").load(`/invoice/edit/${id}`);
};

$( document ).ready(function() {
   $(".main-container #newInvoice").on("click", addInvoice);
   $("div.result-table a.go-to").on("click", function(){gotoEdit(this, 0)});
   $("span.icon-assignment_return.icon-row-action").on("click", function(){gotoEdit(this, 1)});   
});