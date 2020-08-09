class TableResult {
	constructor(jConfig)
	{
		this.jConfig = jConfig;
	}
	
	getHColumn(name, styleValue){
		return `<div class="header-column" style="${styleValue}">
					<span>${name}</span>
				</div>`;
	};

	getHeaderHtml(){
		let hItemsHtml = [];
		this.jConfig.jHeaders.forEach((jn, i) => {
			hItemsHtml.push(this.getHColumn(jn.name, getItemStyle( this.jConfig.cstyle[i] )));
		});
		hItemsHtml.push(this.getAddOrRemoveItemAll());
		return `<div class="header-row">
					${hItemsHtml.join("\n")}
				</div>`;
	};

	//////////////////////////////////////////////////
	
	getBColumn(value, styleValue){
		return `<div class="body-column" style="${styleValue}">
					<span>${value}</span>
				</div>`;
	};

	getAddOrRemoveItemAll(){
		return `<div class="flex-center actions-all-modal" style="width: 10%;">
					<div><span class="add-remove-all">Agregar todos</span></div>
				</div>`;
	}
	
	getAddOrRemoveItem(){
		return `<div class="flex-center actions-items-modal" style="width: 10%;">
					<div><span class="add-remove-item">Agregar</span></div>
				</div>`;
	} 
	
	getColumnsRowHtml(jDataItem){
		let columnsHtml = [];
		const attrsToPaint = this.jConfig.attrs.filter(at => !at.asId);
		attrsToPaint.forEach((jAt,i) => {
			if(jAt.asId) return false;
			const atttValue = this.getJAttrValue(jDataItem, jAt.attr);
			columnsHtml.push(this.getBColumn(atttValue, getItemStyle(this.jConfig.cstyle[i])));
		});
		columnsHtml.push(this.getAddOrRemoveItem());
		return columnsHtml.join("\n");
	};
	
	getBodyRowHtml(jDataItem, attrAsId){
		const columnsRowHtml = this.getColumnsRowHtml(jDataItem);
		const valueId = this.getJAttrValue(jDataItem, attrAsId);
		return `<div id="${valueId}" class="body-row">
					${columnsRowHtml}
				</div>`;
	};

	getBodyRowsHtml(){
		const attrAsId = this.jConfig.attrs.filter(at => at.asId)[0].attr;
		let htmlRows = [];
		this.jConfig.list.forEach(jItem => {
			if(this.jConfig.addedIds.includes(jItem.id)){
				return true;
			}
			htmlRows.push(this.getBodyRowHtml(jItem, attrAsId));
		});
		return `<div class="container-rows">
					${htmlRows.join("\n")}
				</div>`;
	}
	
	getJAttrValue(j, attr){
		const arr = attr.split(".");
		if(arr.length === 1){
			return j[attr];
		}
		let attrs = [];
		arr.forEach(sub => {
			attrs.push(`["${sub}"]`);
		});
		return eval(`j${attrs.join("")}`);
	};

	buildSearcherResultHtml(){
		return `<div class="searcher-result">
					<div class="result-table">
						${this.getHeaderHtml()}
						${this.getBodyRowsHtml()}
					</div>
				</div>`;
	}
}