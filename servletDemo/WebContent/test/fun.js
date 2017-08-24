/*
=================================================================================
运和软件研发人员笔试题：客户端脚本编写能力
考题版本：2014/07/07

考生姓名：
=================================================================================
*/


/*
函数：显示数据/重新显示全部数据
*/
function initData(){
	var oDiv=document.getElementById("container");
	oDiv.innerHTML="";
	//创建table
	var table=document.createElement("table");
	table.border="1px";
	table.width="100%";
	table.style.textAlign="center";
	var thead=document.createElement("thead");
	var tbody=document.createElement("tbody");
	
	var tr=document.createElement("tr");
	
	for(var k in data.title){
		var th=document.createElement("th");
		th.innerHTML=data.title[k];
		tr.appendChild(th);
	}
	
	thead.appendChild(tr);
	table.appendChild(thead);
	
	var length=data.datas.length;
	for(var i=0;i<length;i++){
		var tr=document.createElement("tr");
		for(var k in data.datas[i]){
			var td=document.createElement("td");
			if(data.datas[i][k]==""){
				td.innerHTML="&nbsp;";
			}else{
				td.innerHTML=data.datas[i][k];
			}
			if(k=="birthday"){
				td.birthday=i;
			}
			td.innerHTML=data.datas[i][k];
			tr.appendChild(td);
		}
		tbody.appendChild(tr);
	}
	table.appendChild(tbody);
	oDiv.appendChild(table);
}

function deleteField(){
	
}

/*
函数：计算年龄
*/
function getAge(){
	//由考生完成
	var length=data.datas.length;
	var oDiv=document.getElementById("container");
	var tdList=oDiv.getElementsByTagName("td");
	for(var i=0;i<tdList.length;i++){
		if(tdList[i].birthday>=0){
			var birthday=tdList[i].innerHTML;
			var bArr=birthday.split("-");
			var time=new Date(bArr[0],bArr[1],bArr[2]);
			var age=Math.floor((new Date()-time)/1000/3600/24/365);
			var oAge=tdList[i].previousElementSibling||tdList[i].previousSibling;
			oAge.innerHTML=age;
		}
	}
}

/*
函数：只显示女性
*/
function showGirl(){
	//由考生完成
	var oDiv=document.getElementById("container");
	var tdList=oDiv.getElementsByTagName("td");
	var sexList=[];
	for(var i=0;i<tdList.length;i++){
		//取出性别列
		if(tdList[i].innerHTML=="男"){
			tdList[i].parentNode.style.display="none";
		}
	}
}

/*
函数：增加性别字段
内置函数，禁止修改！
*/
function addField(){
	var fieldCode = "sex";
	var fieldText = "性别";
	data.title[fieldCode] = fieldText;
	for(var ind in data.datas){
		var ran = Math.round(Math.random() * 10);
		var fieldValue = (ran <= 5) ? "男" : "女";
		data.datas[ind][fieldCode] = fieldValue;
	}
	alert("性别字段添加完成！");
}
