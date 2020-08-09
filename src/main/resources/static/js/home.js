const loadProducts = () => {
	$("#workspaceTitle>span").text("Productos"); 
	$("#workspaceContainer").load('/product/list');
};

const dailyOut = () => {
	$("#workspaceTitle>span").text("Salidas/Llegadas");
	$("#workspaceContainer").load('/daily/list');
};

const invoices = () => {
	$("#workspaceTitle>span").text("Facturas");
	$("#workspaceContainer").load('/invoice/list');
};

$( document ).ready(function() {
   $(".home-menu #itemProduct").on("click", loadProducts);
   $(".home-menu #itemDailyOut").on("click", dailyOut);
   $(".home-menu #invoices").on("click", invoices);
});