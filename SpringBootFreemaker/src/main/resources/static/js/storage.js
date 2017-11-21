//设置缓存，如果flag不写或在false都是localStorage,否则sessionStorage
function setSession(key,value,flag){
	var storage = flag?window.sessionStorage:window.localStorage;
	storage.setItem("mk_"+key,value);
};
						
//设置缓存，如果flag不写或在false都是localStorage,否则sessionStorage
function getSession(key,flag){
	var storage = flag?window.sessionStorage:window.localStorage;
	return storage.getItem("mk_"+key);
};
						
//设置缓存，如果flag不写或在false都是localStorage,否则sessionStorage
function removeSession(key,flag){
	var storage = flag?window.sessionStorage:window.localStorage;
	storage.removeItem("mk_"+key);
};