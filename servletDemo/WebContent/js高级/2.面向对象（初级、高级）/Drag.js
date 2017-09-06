function Drag(id){
	this.oDiv=document.getElementById(id);
	this.x=0;
	this.y=0;
	var _this=this;
	this.oDiv.onmousedown=function(){
		_this.fnDown();
		return false;
	}
}

Drag.prototype.fnDown=function(ev){
	var ev=ev||event;
	this.x=ev.clientX-this.oDiv.offsetLeft;
	this.y=ev.clientY-this.oDiv.offsetTop;
	var _this=this;
	document.onmousemove=function(ev){
		_this.fnMove(ev);
	}
	document.onmouseup=function(){
		_this.fnUp();
	}
}

Drag.prototype.fnMove=function(ev){
	var ev=ev||event;
	var _x=ev.clientX-this.x;
	var _y=ev.clientY-this.y;
	this.oDiv.style.left=_x+"px";
	this.oDiv.style.top=_y+"px";
}

Drag.prototype.fnUp=function(){
	document.onmouseup=document.onmousemove=null;
}