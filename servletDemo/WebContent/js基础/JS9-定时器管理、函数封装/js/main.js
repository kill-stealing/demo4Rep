function getStyle(obj,attr){
	return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj,0)[attr];
}
function $(id){return document.getElementById(id);}

function doMove(obj,attr,step,target,endFn){
	step=parseInt(getStyle(obj,attr))<target?step:-step;
	clearInterval(obj.moveTimer);
	obj.moveTimer=setInterval(function(){
		var temp=parseInt(getStyle(obj,attr))+step;
		if(temp>target&&step>0||temp<target&&step<0){
			temp=target;
		}
		obj.style[attr]=temp+"px";
		if(temp==target){
			clearInterval(obj.moveTimer);
			endFn&&endFn();
		}
	},30);
}
function shake(obj,attr,endFn){
	var left=parseInt(getStyle(obj, attr));
	obj.firstLeft?left=obj.firstLeft:obj.firstLeft=left;
	
	clearInterval(obj.timer);
	var arr=[];
	var num=0;
	for(var i=20;i>0;i-=2){
		arr.push(i,-i);
	}
	arr.push(0);
	
	obj.timer=setInterval(function(){
		obj.style[attr]=left+arr[num]+"px";
		num++;
		if(num==arr.length){
			clearInterval(obj.timer);
			num=0;
			endFn&&endFn();
		}
	},50);
}

//perTime 
function doChangeOpacity(obj,perTime,speed,target,endFn){
	clearInterval(obj.opacityTimer);
	var num=0;
	var opacity=getStyle(obj, "opacity");
	speed=opacity<target?speed:-speed;
	obj.opacityTimer=setInterval(function(){
		temp=parseFloat(getStyle(obj, 'opacity'))+speed;
		if(temp>target&&speed>0||temp<target&&speed<0){
			temp=target;
		}
		obj.style.opacity=temp;
		num++;
		if(obj.style.opacity==target){
			clearInterval(obj.opacityTimer);
			num=0;
			endFn&&endFn();
		}
	},perTime); 
}