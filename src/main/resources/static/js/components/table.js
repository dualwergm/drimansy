class Table {
	constructor(jConfig)
	{
		this.jConfig = jConfig;
	}
	
	getBColumn(jCol){
		const style = getItemStyle(jCol.style);
		const value = jCol.value || jCol.vDefault || "";
		let $input = `<span>${value}</span>`;
		if(jCol.asEdit){
			const attrs = getAttrs(jCol.attrs);
			$input = `<input ${attrs} value="${value}" />`;
		}
		return `<div class="body-column" style="${style}">
					${$input}
				</div>`;
	};

	getToDeleteColumn(){
		const style = getItemStyle(this.jConfig.toDelete.style);
		return `<div class="body-column" style="${style}">
			<span class="icon-delete icon-row-action" title="Eliminar"></span>
		</div>`;
	}
	
	getColumnsRowHtml(jRow){
		let columnsHtml = [];
		jRow.columns.forEach((jCol) => {
			columnsHtml.push(this.getBColumn(jCol));
		});
		if(this.jConfig.toDelete){
			columnsHtml.push(this.getToDeleteColumn());
		}
		return columnsHtml.join("\n");
	};
	
	getBodyRowHtml(jRow, oId){
		const columnsRowHtml = this.getColumnsRowHtml(jRow);
		return `<div data-${this.jConfig.data}="${oId}" class="body-row">
					${columnsRowHtml}
				</div>`;
	};

	buildDetailRowsHtml(){
		let htmlRows = [];
		this.jConfig.rows.forEach((jRow,i) => {
			htmlRows.push(this.getBodyRowHtml(jRow, this.jConfig.oIds[i]));
		});
		if(this.jConfig.addContainer){
			return `<div class="container-rows">
						${htmlRows.join("\n")}
					</div>`;
		}
		return htmlRows.join("\n");
	}
}