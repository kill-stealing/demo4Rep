
function getStyle(obj,attr){
	return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj,0)[attr];
}

function startMove(obj,attr,iTarget,fn){
	clearInterval(obj.timer);
	obj.timer=setInterval(function(){
		//取当前值
		var iCur=0;
		if(attr=='opacity'){
			iCur=parseInt(parseFloat(getStyle(obj,attr))*100);
		}else{
			iCur=parseInt(getStyle(obj,attr));
		}
		
		var iSpeed=(iTarget-iCur)/8;
		iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
		console.log(iCur);
		if(iCur==iTarget){
			clearInterval(obj.timer);
			if(fn){
				fn();
			}
		}else{
			if(attr=='opacity'){
				obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
				obj.style.opacity=(iCur+iSpeed)/100;
			}
			else{
				obj.style[attr]=iCur+iSpeed+"px";
			}
		}
	},30);
}

function startMoveNew(obj,json,fn){
	clearInterval(obj.timer);
	obj.timer=setInterval(function(){
		var ifStop=true;
		for(var attr in json){
			//取当前值
			var iCur=0;
			if(attr=='opacity'){
				iCur=parseInt(parseFloat(getStyle(obj,attr))*100);
			}else{
				iCur=parseInt(getStyle(obj,attr));
			}
			
			var iSpeed=(json[attr]-iCur)/8;
			iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
			console.log(iCur);
			if(iCur!=json[attr]){
				ifStop=false;
			}
			if(attr=='opacity'){
				obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
				obj.style.opacity=(iCur+iSpeed)/100;
			}
			else{
				obj.style[attr]=iCur+iSpeed+"px";
			}
		}
		if(ifStop){
			clearInterval(obj.timer);
			if(fn){
				fn();
			}
		}
	},30);
}