var addPayment = () => {
	$(".container-rows").append(getHtmlTemplate('paymentRowTemplate'));
	validateEmptyRows();
};

$( document ).ready(function() {
   $(".main-container #newConcept").on("click", addPayment);
});