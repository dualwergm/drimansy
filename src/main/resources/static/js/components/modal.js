let idsSearcherModal = {add:[], remove:[]};
class Modal {
	constructor(jModal)
	{
		this.jModal = jModal;
	}
	
	getModalContainer({title, content}){
		const $divBtns = this.jModal.btns ? `<div class="container-btns-modal">${this.getBtnsHtml()}</div>` : "";
		const $divTitle = title ? `<div class="flex-center content-modal-title">${title}</div>` : "";
		const style = this.jModal.transparent ? `style="background-color: transparent"` : "";
		const center = this.jModal.transparent ? "flex-center" : "";
		return `<div id="containerModal" class="container-modal">
					<div class="close-modal">X</div>
				  	<div class="content-modal ${center}" ${style}>
				  	${$divTitle}
				   	<div class="content-modal-body">${content}</div>
						${$divBtns}
				  	</div>
				</div>`;
	};

	getBtnsHtml(){
		if(!this.jModal.btns){
			return;
		}
		const btnsHtml = []; 
		this.jModal.btns.forEach((j)=>{
			btnsHtml.push(`<div id="${j.id}" class="item-btn-modal btns btn1">
					<span>${j.title}</span>
				</div>`);
		});
		return btnsHtml.join("\n");
	}
	
	hideModal() {
		$("body").find("#containerModal").removeClass("showModal");
		$("body #containerModal").remove();
	}

	getIdsAdded(){
		const markAsAdded = $("body .content-modal-body .body-row .remove").map((i,o) => console.log($(o).closest(".body-row").attr("id"))).get();
	}	
	
	addOrRemoveAll(){
		const isAddAll = !$(this).parent().hasClass("remove");
		$(this).text(isAddAll ? "Quitar todos" : "Agregar todos");
		const $allItems = $("body .content-modal-body .body-row .add-remove-item");
		$allItems.text(isAddAll ? "Quitar" : "Agregar");
		isAddAll && $($allItems).add(this).parent().addClass("remove");
		!isAddAll && $($allItems).add(this).parent().removeClass("remove");
		if(isAddAll){
			idsSearcherModal.add = $("#containerModal .body-row").map((i,o) => o.id).get();
		}else {
			idsSearcherModal.add = [];
		}
	}

	addOrRemoveItem() {
		const isAdd = !$(this).parent().hasClass("remove");
		isAdd && $(this).parent().addClass("remove");
		!isAdd && $(this).parent().removeClass("remove");
		$(this).text(isAdd ? "Quitar" : "Agregar");
		const id = $(this).closest(".body-row").attr("id");
		if(isAdd) idsSearcherModal.add.push($(this).closest(".body-row").attr("id"));
		else idsSearcherModal.add = $.grep(idsSearcherModal.add, (e)=> e!==id);
	}

	getJResponse(){
		if(isEmpty(idsSearcherModal.add)){
			return [];
		}
		const jResponse = {oIds: idsSearcherModal.add};
		const rows = [];
		idsSearcherModal.add.forEach((id)=>{
			const $row = $(`.content-modal .body-row#${id}`);
			let columns = []
			this.jModal.columns.forEach((jCol) => {
				const jColumn = {...jCol};
				if(jCol.index){
					const $col = $row.find(`div.body-column:nth-child(${jCol.index})`);
					jColumn.value = $col.find(">span").text();
				}
				columns.push(jColumn);
			});
			rows.push({columns});
		});
		jResponse["rows"]=rows;
		return jResponse;
	}
	
	initialiceBtns(oThis){
		if(!this.jModal.btns){
			return;
		}
		$(`.content-modal .container-btns-modal>div`).unbind("click");
		this.jModal.btns.forEach((j)=>{
			if(j.isMain){
				$(`.content-modal .container-btns-modal #${j.id}`).on("click", function(){
					window[j.click](oThis.getJResponse());
				});
			}else{
				$(`.content-modal .container-btns-modal #${j.id}`).on("click", j.click);
			}
		});
	}
	
	getIFrameHtml(){
		const w = getWidthIframe();
		const h = getHeightIframe();
		return `<iframe src="${this.jModal.ifUrl}" onload="onloadIFrameModal(${this.jModal.ifAfterLoad})" width="${w}" height="${h}" frameBorder="0" id="${this.jModal.ifId}" name="${this.jModal.ifId}" scrolling="auto" ></iframe>`;
	}
	
	initialiceModal() {
		$("body").find("#containerModal>div.close-modal").on("click", this.hideModal);
		$("body .content-modal-body .header-row .add-remove-all").on("click", this.addOrRemoveAll);
		$("body .content-modal-body .body-row .add-remove-item").on("click", this.addOrRemoveItem);
		this.initialiceBtns(this);
	};

	showModal(){
		$("body").append(this.getModalContainer(this.jModal));
		$("body").find("#containerModal:not(.showModal)").addClass("showModal");
		this.initialiceModal();
	};
	
	showIFrameModal(){
		this.jModal.content = this.getIFrameHtml();
		this.showModal();
	}
}
