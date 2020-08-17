var getTotals = () => {
	let totalOut = 0;
	let totalIn = 0;
	$(".container-rows .product-item").each((i,o) => {
		totalOut += $(o).find("input.d-out").val() * $(o).find("span.d-price").text();
		totalIn += $(o).find("input.d-in").val() * $(o).find("span.d-price").text();
	} );
	return {totalOut, totalIn};
};

var saveArriveDate = () => $("#isArrive").val() === "1" || !isEmpty($("#arriveDateOld").val());

var getJMaster = () => {
	let jMaster = getInputValuesMaster();
	if(!saveArriveDate()){
		jMaster.arriveDate="";
	}
	return jMaster;
};

var getJDetail = () => {
	let jDetails = [];
	$(".container-rows .product-item").each((i,o) => {
		let jDetail = {
			id: o.id,
			productId: $(o).data().product,
			amountOut: $(o).find("input.d-out").val(),
			amountIn: $(o).find("input.d-in").val(),
			price: $(o).find("span.d-price").text()
		}
		jDetails.push(jDetail);
	} );
	return jDetails;
};

var updateTotal = () => {
	const {totalOut, totalIn} = getTotals();
	$("#totalOutSpan").text(totalOut);
	$("#arriveTotalSpan").text(totalIn);
	$("#totalSpan").text(totalOut-totalIn);
	$("#totalOut").val(totalOut);
	$("#arriveTotal").val(totalIn);
};

var saveOutIn = () => {
	if(!validateRequired()){
		return false;
	}
	const jParams = {
			jMaster: getJMaster(),
			jDetail: getJDetail()
	};
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/daily/save",
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

var addInvoice = () => {
	console.log("add");
};

var showAddInvoiceBtn = () => $("#isArrive").val() === "1" && !isEmpty($("#arriveDateOld").val());

var validateInvoiceBtn = () => {
	if(showAddInvoiceBtn()){
		$(".btn2#addInvoice").show();
		$(".btn2#addInvoice").on("click", addInvoice);
	}
};

$( document ).ready(function() {
   $(".btn1#save").on("click", saveOutIn);
   $(".container-rows").find(".d-out, .d-in").on("keyup", updateTotal);
   validateInvoiceBtn();
   updateTotal();
});