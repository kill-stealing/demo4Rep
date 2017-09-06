function LimitDrag(id){
	Drag.call(this,id);
}

for(var i in Drag.prototype){
	LimitDrag.prototype[i]=Drag.prototype[i];
}

LimitDrag.prototype.fnMove=function(){
	var ev=ev||event;
	var _x=ev.clientX-this.x;
	var _y=ev.clientY-this.y;
	
	if(_x<0){
		_x=0;
	}else if(_x>document.documentElement.clientWidth-this.oDiv.offsetWidth){
		_x=document.documentElement.clientWidth-this.oDiv.offsetWidth;
	}
	if(_y<0){
		_y=0;
	}else if(_y>document.documentElement.clientHeight-this.oDiv.offsetHeight){
		_y=document.documentElement.clientHeight-this.oDiv.offsetHeight;
	}
	
	this.oDiv.style.left=_x+"px";
	this.oDiv.style.top=_y+"px";
	
}