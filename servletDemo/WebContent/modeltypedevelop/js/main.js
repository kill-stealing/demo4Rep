require.config({
	paths:{
		"jquery":"jquery-3.2.1"
	}
});




define("main",["jquery"],function($){
	$(".wrap input").click(function(){
		$(".wrap input").attr("class","");
		$(this).attr("class","active");
		$(".wrap div").hide();
		$(".wrap div").eq($(this).index()).show();
	});
	
	
	var div2=document.getElementById("div1");
	
	/*require("drag.js",function(drag){
		
	});*/
	
	require(["drag"],function(drag){
		drag.drag(div2);
	    //todo
	});
	
	// console.log(window.asd = drag);
	
});