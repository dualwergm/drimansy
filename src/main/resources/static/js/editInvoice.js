/**
 * cstyle: un 4% es para el check de selección
 */
var configProductModal = {
	jHeaders: [
		{name: "Nombre"},
		{name: "Categoría"},
		{name: "Sabor"},
		{name: "Tamaño"},
		{name: "Precio"}
	],
	cstyle: [{width: "34%"}, {width: "18%"}, {width: "18%"}, {width: "10%"}, {width: "8%"}],
	attrs: [{attr: "id", asId: true}, {attr: "name"}, {attr: "categoryVO.name"}, {attr: "flavorVO.name"}, {attr: "sizeVO.name"}, {attr: "price"}],
	addedIds: [],
	list: []
};

var calculateInvoiceValuesGroup = () => {
	let total = 0;
	$(".result-table .inv-amount").each((_i,o) => {
		const $row = $(o).closest(".body-row");
		const price = $row.find(".inv-price").val();
		total += (o.value * price);
	});
	let totalPayment = $("#totalPayment").val();
	$("#totalSpan").text(total);
	$("#total").val(total);
	$("#toPaySpan").text(total - totalPayment);
};

var toDeleteInvoice = function() {
	const $row = $(this.closest(".body-row"));
	if(!$($row).attr("id")){
		$row.remove();
		return;
	}
	if(!confirm("Seguro quieres eliminar este producto de la factura?")){
		return;
	}
	const id = $row.attr("id");
	$.post(`/invoice/detail/delete?detailId=${id}`,
			null,
			function(jData){
				jData.afterOf = function(){
					$row.remove();
					refreshTotalPayments();
				};
				ajaxResponse(jData);
			}
	);
};

var setInvoiceTableHandler = () => {
	$(".icon-delete.icon-row-action").unbind("click").on("click", toDeleteInvoice);
	$(".container-rows .body-row input[type=number]").unbind("keyup").on("keyup", calculateInvoiceValuesGroup);
	calculateInvoiceValuesGroup();
};

function addRowsFromModal(jResponse){
	let $container = $(".detail-container .container-rows");
	const hasContainer = $container.length > 0;
	jResponse.addContainer = !hasContainer;
	jResponse.toDelete = {style: {width: "5%"}};
	jResponse.data = "productid";
	const table = new Table(jResponse);
	if(!hasContainer){
		$container = $(".detail-container .result-table");
	}
	$container.append(table.buildDetailRowsHtml());
	hideModal();
	setInvoiceTableHandler();	
}

var hideModal = () => {
	invoiceModal.hideModal();
};

var getForModalIds = () => {
	return $(".detail-container .container-rows .body-row").map((i,o) => $(o).data().productid).get();
};

var invoiceModal;

var showProductsModal = (jResult) => {
	configProductModal.list = jResult.list;
	configProductModal.addedIds = getForModalIds();
	const tResult = new TableResult(configProductModal);
	const resultHtml = tResult.buildSearcherResultHtml();
	invoiceModal = new Modal({title: "Agregar los productos de la factura", 
		content: resultHtml,
		columns: [
				{index: 1, style: {width: "55%"} }, 
				{asEdit: true, style: {width: "20%"}, vDefault: 1, attrs: {type: "number", min: 1, class: "inv-amount"} }, 
				{index: 5, asEdit: true, style: {width: "20%"}, attrs: {type: "number", min: "0", class: "inv-price"} }
			],
		btns:[
			{id: "cancel", title: "Cancelar", click: hideModal},
			{id: "accept", title: "Listo", click: "addRowsFromModal", isMain: true},
		]
	}) 
	invoiceModal.showModal();
};

var getProductsToAdd = () => {
	$.get('/product/response', {}, showProductsModal);
};

function loquesea(){
	console.log("lo que seaaaaaaaaa");
}

var getPayments = () => {
	const id = $("#id").val();
	var paymentModal = new Modal({title: "Abonos", 
		ifUrl: `/payment/list?invoiceId=${id}&isModal=1`,
		ifId: 'paymentsModal',
		ifAfterLoad: "loquesea"
	}) 
	paymentModal.showIFrameModal();
};

var initializeAdds = () => {
	$("#addDetails").on("click", getProductsToAdd);
	$("#addPayment").on("click", getPayments);
};

var getDataInvoiceDetail = () => {
	let jDetails = [];
	$(".detail-container .container-rows .body-row").each((_i,o) => {
		let jDetail = {
			id: o.id,
			productId: $(o).data().productid,
			amount: $(o).find("input.inv-amount").val(),
			price: $(o).find("input.inv-price").val()
		}
		jDetails.push(jDetail);
	} );
	return jDetails;
};

var validateInvoiceSave = () => {
	if(!validateRequired()){
		return false;
	}
	if($(".detail-container .container-rows .body-row").length === 0){
		alert("La factura debe tener por lo menos un producto.");
		return false;
	}
	return true;
};

function refreshTotalPayments(){
	const id = $("#id").val();
	$.get(`/payment/total?invoiceId=${id}`,
			null,
			function(data){
				const jData = JSON.parse(data.jresponse);
				$("#totalPayment").val(jData.totalPayments);
				calculateInvoiceValuesGroup();
			}
	);
}  

var saveInvoice = () => {
	if(!validateInvoiceSave()){
		return;
	}
	const jParams = {
		jMaster: getInputValuesMaster(),
		jDetail: getDataInvoiceDetail()
	};
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/invoice/save",
        data: JSON.stringify(jParams),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
            new AjaxResponse(data).process();
        },
        error: function (e) {
        	console.log(e);
        }
	});
};

$( document ).ready(function() {
	setInvoiceTableHandler();
	initializeAdds();
   $(".container-btn-bottom #save").unbind("click").on("click", saveInvoice);
});