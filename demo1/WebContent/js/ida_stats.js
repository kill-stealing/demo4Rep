if(!window.console){window.console={log:function(){},error:function(){},warn:function(){}}}if(window.isIdaStatsLoaded){console.log("+++DBDM-LOG > ida_stats.js > ida_stats.js has already been loaded, exiting.")}else{window.isIdaStatsLoaded=true;(function(){var b=["cmCreatePageviewTag","cmCreateProductviewTag","cmCreateShopAction5Tag","cmDisplayShops","cmCreateShopAction9Tag","cmCreateOrderTag","cmCreateRegistrationTag","cmCreateElementTag","cmCreateConversionEventTag","cmCreateManualPageviewTag","cmCreateManualLinkClickTag","cmCreateManualImpressionTag","cmCreateCustomTag","cmSetupOther","cmSetCurrencyCode","cmDisplayShop9s","cmDisplayShop5s","ibmStats.event","bindPageViewWithAnalytics"];
window.ghostQueue=[];(function f(){if(!d()){for(var g=0;g<b.length;g++){c(b[g])}a()}})();function d(){for(var g=0;g<b.length;g++){if(b[g].indexOf(".")===-1){if(typeof(window[b[g]])!=="function"||window[b[g]].isGhost){return false}}else{if(typeof(window[b[g].split(".")[0]])!=="undefined"){if(typeof(window[b[g].split(".")[0]][b[g].split(".")[1]])!=="function"||window[b[g].split(".")[0]][b[g].split(".")[1]].isGhost){return false}}else{return false}}}return true}function c(g){if(g.indexOf(".")===-1){window[g]=function(){window.ghostQueue.push({functionName:g,args:arguments})
};window[g].isGhost=true}else{window[g.split(".")[0]]=window[g.split(".")[0]]||{};window[g.split(".")[0]][g.split(".")[1]]=function(){window.ghostQueue.push({functionName:g,args:arguments})};window[g.split(".")[0]][g.split(".")[1]].isGhost=true}}function a(){setTimeout(function(){if(d()){e()}else{a()}},50)}function e(){for(var g=0;g<window.ghostQueue.length;g++){if(window.ghostQueue[g].functionName.indexOf(".")===-1){window[window.ghostQueue[g].functionName].apply(this,window.ghostQueue[g].args)}else{window[window.ghostQueue[g].functionName.split(".")[0]][window.ghostQueue[g].functionName.split(".")[1]].apply(this,window.ghostQueue[g].args)
}}}})();var v16elu={NTPT_DOWNLOADTYPES:"bqy,doc,dot,exe,flv,jpg,png,mov,mp3,pdf,pps,ppt,rss,sh,swf,tar,txt,wmv,xls,xml,zip,avi,eps,gif,lwp,mas,mp4,pot,prz,rtf,wav,wma,123,odt,ott,sxw,stw,docx,odp,otp,sxi,sti,pptx,ods,ots,sxc,stc,xlsx",NTPT_DOMAINLIST:".ibm.co,.ibm.com,.lotuslive.com,.cognos.com,.webdialogs.com,.servicemanagementcenter.com,.xtify.com,.ibmcloud.com,.ibmdw.net,.bluemix.net,.smartercitiescloud.com",evhndlr:true,domainBlacklist:".ibm.com,.mitre.org,.learnquest.com",storeTealiumPageviewData:function(){var e=new Array();
window.pageViewAttributes="";var h=JSON.stringify(window.utag.sender).split(/[}]/);for(var d=0;d<h.length;d++){var m=h[d].split("{")[0],g=h[d].split("{")[1];if(m.indexOf("map")!==-1&&typeof g!=="undefined"){var f=g.split(",");for(var c=0;c<f.length;c++){if(typeof f[c].split(":")[1]!=="undefined"&&f[c].split(":")[1].indexOf("PageviewTag_pv_a")!==-1){var l=f[c].split(":")[0].replace(/[""]/g,""),b=f[c].split(":")[1].substring(17,f[c].split(":")[1].length-1);if(typeof utag.data[l]!=="undefined"&&(e[b]==""||e[b]==undefined)){e[b]=utag.data[l]
}else{if(typeof utag.data[l]!=="undefined"&&l.indexOf("meta.")!==-1){e[b]=utag.data[l]}}}}}}for(var d=1;d<=e.length;d++){window.pageViewAttributes+=e[d]+"-_-"}},onPageLoad:function(){if(window.utag&&window.utag.sender){v16elu.storeTealiumPageviewData()}},utilstatsHelper:function(a){ibmStats.event(a)},init:function(){var b=this;window.loadingTime=new Date().getTime();if(typeof(window.cmTagQueue)=="undefined"){window.cmTagQueue=[]}if(typeof(document.domain)!=="undefined"&&document.domain.indexOf("ibm.com")!==-1){window.cmTagQueue.push(["cmSetupCookieMigration",true,true,null,this.domainBlacklist])
}if(typeof(document.domain)!=="undefined"&&document.domain.indexOf("ibm.com")==-1){window.cmTagQueue.push(["cmSetupCookieMigration",true,true,this.NTPT_DOMAINLIST])}window.cmTagQueue.push(["cmSetupOther",{cm_JSFEAMasterIDSessionCookie:true}]);if(typeof(document.domain)!=="undefined"&&document.domain.indexOf("ibm.com")==-1){requestServerCall=function(c){var d=document.createElement("script");d.type="text/javascript";d.src=c;document.getElementsByTagName("head")[0].appendChild(d)},IBMISE_BOOTSTRAP=function(c){if(c.IBMer){window.NTPT_IBMer=c.IBMer
}if(c.IBMIXS){window.IBMIXS=c.IBMIXS}};requestServerCall("//www.ibm.com/gateway/gp/getProfile/?cb=260:IBMISE_BOOTSTRAP&cc=us&lc=en")}try{(function(){window.IBMDependencyRegistry=window.IBMDependencyRegistry||{isLoaded:{},listeners:[],check:function(e){for(var d=0,c=e.length;d<c;d++){if(!this.isLoaded[e[d]]){return false}}return true},on:function(c,d){if(typeof d!=="function"){return false}if(typeof c==="string"){c=[c]}if(this.check(c)){d()}else{this.listeners.push({dependencies:c,callback:d})}},emit:function(d){this.isLoaded[d]=1;
var c=[];for(var e=this.listeners.length-1;e>-1;e--){var f=this.listeners[e];if(this.check(f.dependencies)){c.push(this.listeners.splice(e,1)[0])}}for(e=0;e<c.length;e++){c[e].callback()}}}})()}catch(a){console.log("+++DBDM-ERROR > ida_stats.js > IBMDependencyRegistry: "+a)}(function(f,e,h,g){f="//tags.tiqcdn.com/utag/ibm/main/prod/utag.js";e=document;h="script";g=e.createElement(h);g.src=f;g.type="text/java"+h;g.async=true;f=e.getElementsByTagName(h)[0];f.parentNode.insertBefore(g,f);g.onload=function(){if(typeof window.utag!=="undefined"&&typeof window.utag.data!=="undefined"){v16elu.siteID=window.utag.data.site_id||"";
if(v16elu.siteID.toLowerCase()=="ecom"){window.cmTagQueue.push(["cmSetupNormalization","krypto-_-krypto"])}if(v16elu.siteID.toLowerCase()=="p4sc"){window.cmTagQueue.push(["cmSetupOther",{cm_JSFEAMasterIDSessionCookie:true,cm_FormPageID:true}])}else{window.cmTagQueue.push(["cmSetupOther",{cm_JSFEAMasterIDSessionCookie:true}])}}}})();if(window.addEventListener){window.addEventListener("load",v16elu.onPageLoad,false)}else{if(window.attachEvent){window.attachEvent("onload",v16elu.onPageLoad)}}}};if(typeof(window.ida_eluminate_enabled)!=="undefined"||typeof(window.tealium_enabled)!=="undefined"){if(!window.ida_eluminate_enabled||!window.tealium_enabled){}else{v16elu.init()
}}else{v16elu.init()}};