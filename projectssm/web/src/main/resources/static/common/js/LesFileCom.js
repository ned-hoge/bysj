/*判断是否ie浏览器*/
function isIE() {
    try{
        if (!!window.ActiveXObject || "ActiveXObject" in window){
            return true;
        }else{
            return false;
        }
    }catch(ex){
        return false;
    }

}

/* 检测 LES ActiveX 控件 是否可用 */
function TryLESAX(){
    return (null !== _NewAxObj('LesAxCtrl.LesUtilX', true, true));
}
/* 检测 浏览器是否支持HTML 5... */
function TryHTML5() {
    if(!_NewXhrObj(true)) return false;
    var api=['File', 'FileReader', 'FileList', 'Blob', 'FormData', 'WebSocket'];
    for(var i=api.length-1; i>=0; i--) if(!(api[i] in window)) return false;
    return true;
}

/* 创建浏览器原生的 'XMLHttpRequest' 对象, 用于不支持LES 控件的情况 */
function _NewXhrObj(bNoAlert)
{
    var obj = null;
    try {
        obj = new window.XMLHttpRequest();
    }
    catch(ex) {
        try {
            obj = new window.ActiveXObject( "Microsoft.XMLHTTP" );
        }
        catch(ex) {
            obj = null;
            if(!bNoAlert) alert( '(_NewXhrObj)ERROR: Can not create "XMLHttpRequest" object.\r\n\r\n' + ex.description );
        }
    }
    return obj;
}

/* 创建 ActiveXObject 控件底层函数  */
function _NewAxObj(AxID, bSingleInstance, bNoAlert)
{
    var obj = null;
    if(bSingleInstance && window[AxID]) return window[AxID];
    try {
        obj = new ActiveXObject(AxID);
        if(bSingleInstance) window[AxID] = obj;
    }
    catch(ex) {
        if(!bNoAlert) alert( '(_NewAxObj)ERROR: Can not create "'+ AxID +'" object.\r\n\r\n' + ex.description );
        obj = null;
    }
    return obj;
}

var _LESFILE_OLEDOC_EXTS = '*.doc; *.docx; *.xls; *.xlsx; *.ppt; *.pptx; *.wps; *.et; *.dps';

function _LesFile_IsOleFile(FileID){
    var fin = _LesFile_GetFileInfo(FileID);
    var ext = fin['FILE_NAME'].toLowerCase();
    ext = '*.' + ext.split('.').pop(); //取文件名后缀
    return (_LESFILE_OLEDOC_EXTS.indexOf(ext)>=0);
}

/** 屏幕的大小 **/
var _screen_width_height_pix = [window.screen.availWidth, window.screen.availHeight];

/** 打开简化的子窗口, name名称可选, 宽度sizeW, 高度sizeH, 左边posL, 上边posT 为像素值 **/
function ShowWin(url, name, sizeW, sizeH, posL, posT)
{
    var fc = false;
    if(arguments.length<=2)  fc = true; // 不传大小, 默认全屏

    var w = $_tonum(sizeW,600);
    var h = $_tonum(sizeH,400);

    if(w >= _screen_width_height_pix[0] && h>=_screen_width_height_pix[1]) fc = true;

    var l = 0, t = 0;
    if(fc) {
        w = _screen_width_height_pix[0]-18; // 全屏
        h = _screen_width_height_pix[1]-64;
        l = 0;
        t = 0;
    }
    else {
        if(w>0 && w < 1) w = w * _screen_width_height_pix[0];
        if(h>0 && h < 1) h = h * _screen_width_height_pix[1];
        w = Math.max(100, Math.min(w, _screen_width_height_pix[0]-0));
        h = Math.max(100, Math.min(h, _screen_width_height_pix[1]-0));
        l = $_tonum(posL, (_screen_width_height_pix[0] - w)/2);
        t = $_tonum(posT, (_screen_width_height_pix[1] - h)/2);
    }

    var ow =  window.open(url, $_tostr(name), (fc?'xxfullscreen=yes,':'') + 'height='+(h)+',width='+(w)+',top='+(t)+',left='+(l)+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=yes,status=no');
    try { ow.focus(); } catch(ex) { }
    return ow;
}

/* 提交参数的合成, 传入Object自动合成为 name=value&name=value...的格式, 注意对象属性不能重名,有局限 */
function _BuildParams(oParam)
{
    if($_str(oParam)) return oParam; // 字符串原样返回
    if(!$_obj(oParam)) return $_tostr(oParam);

    var p = oParam;
    var a = [];
    for(k in p) {
        var v = $_tostr(p[k]);
        a.push(encodeURIComponent(k) + '=' + encodeURIComponent(v));
    }
    return a.join('&');
}
/* 生成32位 Guid, 随机由0-9,a-f 组成, 全小写 */
function Guid32()
{
    var xchars = '0123456789abcdef'.split('');
    var a = [];
    for(var i=0; i<32; i++){
        a[i] = xchars[Math.floor(16 * Math.random())];
    }
    return a.join('');
}
/* 生成16位 Guid, 随机由0-9,a-z,A-Z 组成 */
function Guid16()
{
    var xchars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var a = [];
    for(var i=0; i<16; i++){
        a[i] = xchars[Math.floor(62 * Math.random())];
    }
    return a.join('');
}


/** 类型判断 实用函数 **/
function $_und(v)  { return !!(typeof(v)=='undefined'); }
function $_null(v) { return !!(v===null); }
function $_nan(v)  { return  !(v===v); }
function $_num(v)  { return !!(isFinite(v-0)); }
function $_obj(v)  { return !!((v && typeof(v)=='object') || (v && ''+v.constructor==''+Object)); }
function $_str(v)  { return !!(typeof(v)=='string'   || (v && ''+v.constructor==''+String  ) ); }
function $_bool(v) { return !!(typeof(v)=='boolean'  || (v && ''+v.constructor==''+Boolean ) ); }
function $_func(v) { return !!(typeof(v)=='function' || (v && ''+v.constructor==''+Function) ); }
function $_arr(v)  { return !!((Array.isArray && Array.isArray(v)) || (v && typeof(v.push)=='function' && typeof(v.shift)=='function')); }
function $_date(v) { return !!((Date.isDate   && Date.isDate(v))   || (v && typeof(v.getFullYear)=='function' && typeof(v.getUTCFullYear)=='function')) ; }

/** 转 string, undefined, null, NaN, 正负无穷 都返回空字符串 **/
function $_tostr(v) { return ($_und(v) || $_null(v) || $_nan(v)) ? '' : (''+v); }
/** 转 number, 如果转不成功返回 def **/
function $_tonum(v, def) {
    var nv = $_tostr(v); if(nv=='') return def;
    return ($_num(nv)) ? (nv-0) : def;
}