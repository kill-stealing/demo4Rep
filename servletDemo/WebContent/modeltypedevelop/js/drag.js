define(["jquery"],(function($){
	var aaa = {
			drag : function(obj){
				var disX=0;
				var disY=0;
				console.log(obj);
				obj.onmousedown=function(ev){
					var ev=ev||event;
					disX=ev.clientX-obj.offsetLeft;
					disY=ev.clientY-obj.offsetTop;
					document.onmousemove=function(ev){
						var ev=ev||event;
						var L=ev.clientX-disX;
						var T=ev.clientY-disY;
						obj.style.left=L+"px";
						obj.style.top=T+"px";
					}
					document.onmouseup=function(){
						document.onmouseup=document.onmousemove=null;
					}
					return false;
				}
			}
	};
	
	var aaa1=(function(){
		
	})();
	return aaa;
}));