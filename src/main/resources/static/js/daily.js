var addDailyOut = () => {
	$("#workspaceTitle>span").text("Nueva salida");
	const jparamsStr = encodeURIComponent(JSON.stringify({}));
	$("#workspaceContainer").load(`/daily/out?jparams=${jparamsStr}`);
};

var gotoEdit = function (obj, arrive){
	$("#workspaceTitle>span").text(arrive===0?"Editar salida":"Editar llegada");
	const $rowItem = $(obj).closest('div.daily-item');
	const id = parseInt($rowItem.attr("id"));
	$("#workspaceContainer").load(`/daily/out/${id}?isArrive=${arrive}`);
};

$( document ).ready(function() {
   $(".main-container #newConcept").on("click", addDailyOut);
   $("div.result-table a.go-to").on("click", function(){gotoEdit(this, 0)});
   $("span.icon-assignment_return.icon-row-action").on("click", function(){gotoEdit(this, 1)});   
});