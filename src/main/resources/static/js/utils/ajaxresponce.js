class AjaxResponse {
	constructor(jResponse)
	{
		this.jResponse = jResponse;
	}
	
	getMsgTime(){
		return this.jResponse.status === "OK" ? 3000 : 5000;
	}
	
	getHtmlError(){
		return `<div class="">
					<div style="margin-bottom: 30px;"><span class="icon-sad c-error icon-big"></span></div>
					<div class="tx-big c-white bold">Ouch... Hubo un error!</div>
				</div>`;
	}
	
	getHtmlSuccess(){
		return `<div class="">
					<div style="margin-bottom: 30px;"><span class="icon-smile c-success icon-big"></span></div>
					<div class="tx-big c-white bold">Todo bien!!!</div>
				</div>`;
	}
	
	getHtmlResponse(){
		return this.jResponse.status === "OK" ? 
				this.getHtmlSuccess() : this.getHtmlError();
	}
	
	process(){
		const mResponse = new Modal({ 
			content: this.getHtmlResponse(),
			transparent: true
		}) 
		mResponse.showModal();
		const executeCallback = this.jResponse.status === "OK" && this.jResponse.afterOf; 
		setTimeout(function(){
			mResponse.hideModal();
			if(executeCallback){
				executeCallback();
			}			
		}, this.getMsgTime());
	}
}