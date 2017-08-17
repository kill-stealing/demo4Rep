function getStyle(obj,attr){
	return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj,0)[attr];
}
function $(id){return document.getElementById(id);}

function doMove(obj,attr,step,target,endFn){
	step=parseInt(getStyle(obj,attr))<target?step:-step;
	clearInterval(obj.timer);
	obj.timer=setInterval(function(){
		var temp=parseInt(getStyle(obj,attr))+step;
		if(temp>target&&step>0||temp<target&&step<0){
			temp=target;
		}
		obj.style[attr]=temp+"px";
		if(temp==target){
			clearInterval(obj.timer);
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
function doChangeOpacity(obj,perTime,speed,target){
	clearInterval(obj.timer);
	var num=0;
	var opacity=getStyle(obj, "opacity");
	if(!!opacity){
		opacity=1;
	}
	obj.timer=setInterval(function(){
		
		if(obj.style.opacity==target){
			obj.style.opacity=target;
		}
		
		if(opacity>target){
			obj.style.opacity=opacity-num*speed;
		}else if(opacity<target){
			obj.style.opacity=opacity+num*speed;
		}
		num++;
		if(obj.style.opacity==target){
			clearInterval(obj.timer);
			num=0;
		}
	},perTime); 
}