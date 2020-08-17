var isEmpty = s => s === null || s.length === 0;

const getItemStyle = jStyle => {
	let styles=[];
	$.each(jStyle, (n,v) => {
		styles.push(`${n}: ${v};`);
	});
	return styles.join(" ");
}

const getAttrs = jAttrs => {
	let attrs=[];
	$.each(jAttrs, (n,v) => {
		attrs.push(`${n}="${v}"`);
	});
	return attrs.join(" ");
}

const getInputValues = ($selector) =>{
	let jMaster = {};
	$selector.each((_i,o) => {
		jMaster[o.id] = $(o).val();
	});
	return jMaster;
};

const getInputValuesMaster = () => {
	let jMaster = getInputValues($(".master-container").find("input,select"));
	jMaster.userId=$('#userId').val();
	return jMaster;
};

const getInputValuesRow = ($row) => {
	return getInputValues($row.find("input,select"));
};

const setBError = ($o) => {
	$o.addClass("b-error");
	setTimeout(()=> {
		$o.removeClass("b-error");
	}, 5000);
};

const validateRequired = () => {
	let valid = true;
	$('input[required], select[required]').each((i,o)=>{
		if(isEmpty($(o).val())){
			valid = false;
			let label = $(o).siblings("label").text();
			alert(`El campo ${label} no puede quedar vacío.`);
			setBError($(o));
			return false;
		}
	});
	return valid;
};

const validateRequiredDetails = (msg) => {
	let valid = true;
	$('input[required], select[required]').each((i,o)=>{
		if(isEmpty($(o).val())){
			valid = false;
			alert(msg);
			setBError($(o).closest(".body-row"));
			return false;
		}
	});
	return valid;
};

const getWidthIframe = () => window.innerWidth - 150;

const getHeightIframe = () => window.innerHeight - 150;

const getHtmlTemplate = idTpl => document.importNode( document.querySelector(`#${idTpl}`).content , true);

const onloadIFrameModal = (afterLoad) => {
	$(".content-modal-body iframe").contents().find("body").css("backgroundColor", "initial");
	$(".content-modal-body iframe").contents().find("body .container-btn-bottom").css("bottom", "50px");
	$(".content-modal-body iframe").contents().find("body .main-container").css({
		height: "90%",
		overflowY: "auto"
	});
	if(afterLoad){
		afterLoad();
	}
};

const validateEmptyRows = () => {
	$(".container-rows .body-row").length <= 0 ? 
			$(".container-empty").show() : $(".container-empty").hide();
};

function hideLastModal(){
	$("body #containerModal").last().remove();
}

function ajaxResponse(jData){
	new AjaxResponse(jData).process();
}