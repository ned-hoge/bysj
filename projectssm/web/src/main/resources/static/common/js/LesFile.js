var $lesys_file_path$ = "http://36.154.177.18:8191/FILE";

var _les_file_rpc_api    = $lesys_file_path$ +  '/LesFileRpcApiService';
var _les_file_rpc_upstrm = $lesys_file_path$ +  '/LesFileRpcUploadStreamService';
var _les_file_rpc_upform = $lesys_file_path$ +  '/LesFileRpcUploadFormService';

/*子页面关闭回调*/
var okvalue = {};
function setOkValue(v){
    okvalue = v;
}
/*html5上传文件*/
function LesFile_Upload(baseFilePath,ParamObj, fnCallback)
{
    okvalue = {};
    var fcb = fnCallback;
    var pmo = ParamObj || {};
    var fid = $_tostr(pmo['FileID']);   if(!fid) fid = Guid32(); // 自动生成 file_id，附件id存在则覆盖
    var fmx = $_tonum(pmo['MaxSize'], 0);
    var fna = $_tostr(pmo['FileName']);
    var fty = $_tostr(pmo['FileType']);
    var fsc = $_tostr(pmo['FileSource'])||window.location.href;//上传来源
    if(!$_func(fcb)) throw new Error('(LesFile_Upload)ERROR: 参数 fnCallback 必须为 Function 类型');
    layer.open({
        type: 2
        , title: '上传附件'
        , content: baseFilePath+'/lesysfile/lesys_file_h5.html'
        , maxmin: false
        , area: ['400px', '200px']
        , closeBtn:0
        , offset:'auto'
        ,success: function(layero, index){
            window[layero.find('iframe')[0]['name']].setParam({'FileID': fid, 'MaxSize': fmx, 'FileName': fna, 'FileType': fty, 'FileSrc': fsc,'FilePath':_les_file_rpc_upstrm} ); //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
        }
        ,end:function(){
            if(okvalue.err!= 'USER_ABORTED'){
                fcb(okvalue.err||'', okvalue.fid, okvalue.fna,okvalue.fsz);
            }
        }
    });
    return;
}

/*html5多上传文件*/
function LesFile_Uploads(ParamObj, fnCallback)
{
    okvalue = {};
    var fcb = fnCallback;
    var pmo = ParamObj || {};
    var fmx = $_tonum(pmo['MaxSize'], 0);
    var fty = $_tostr(pmo['FileType']);
    var fsc = $_tostr(pmo['FileSource'])||window.location.href;//上传来源
    if(!$_func(fcb)) throw new Error('(LesFile_Uploads)ERROR: 参数 fnCallback 必须为 Function 类型');

    layer.open({
        type: 2
        , title: '上传附件'
        , content: '/ywyd/lesysfile/lesys_file_h5s.html'
        , maxmin: false
        , area: ['600px', '280px']
        , closeBtn:0
        , offset:'auto'
        ,success: function(layero, index){
            window[layero.find('iframe')[0]['name']].setParam({'MaxSize': fmx,'FileType': fty, 'FileSrc': fsc,'FilePath':_les_file_rpc_upstrm} ); //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
        }
        ,end:function(){
            if(okvalue.err!= 'USER_ABORTED'){
                fcb(okvalue.err||'', okvalue.fids, okvalue.fnas,okvalue.fszs);
            }
        }
    });
    return;
}

/*下载文件*/
function LesFile_Download(FileID){
    // 首先获取文件信息
    var fin = _LesFile_GetFileInfo(FileID);
    // 通过浏览器弹窗下载
    var url = fin['FILE_URL']; //window._les_file_rpc_api +'?'+ _BuildParams({'cmd': 'download.security', 'file_id': FileID});
    ShowWin(url, '',  600, 400);
    return fin['FILE_NAME']; // 不能得到路径, 仅返回文件名
}
/*删除文件*/
function LesFile_Delete(FileID){
    var r = "";
    $.ajax({
        url: _les_file_rpc_api+'?'+_BuildParams({'cmd':'delete','file_id':$_tostr(FileID)}),
        type: 'post',
        cache: false,
        async: false,
        contentType : 'application/json; charset=utf-8',
        success: function (text) {
            r = text;
        }
    });
    if(r.charAt(0)!='!') _throw_rpc_error('LesFile_Delete', r);
    return true; // 成功
}
/*重命名文件*/
function LesFile_Rename(FileID, NewFileName)
{
    var r = "";
    $.ajax({
        url: _les_file_rpc_api+'?'+_BuildParams({'cmd':'rename','file_id':$_tostr(FileID),'file_name':$_tostr(NewFileName)}),
        type: 'post',
        cache: false,
        async: false,
        contentType : 'application/json; charset=utf-8',
        success: function (text) {
            r = text;
        }
    });
    if(r.charAt(0)!='!') _throw_rpc_error('LesFile_Rename', r);
    return true; // 成功
}
/*复制文件*/
function LesFile_Copy(FileID){
    var r = "";
    $.ajax({
        url: _les_file_rpc_api+'?'+_BuildParams({'cmd':'copy','file_id':$_tostr(FileID)}),
        type: 'post',
        cache: false,
        async: false,
        contentType : 'application/json; charset=utf-8',
        success: function (text) {
            r = text;
        }
    });
    if(r.charAt(0)!='!') _throw_rpc_error('LesFile_Delete', r);
    return r.substring(1);
}


var  _LesFile_oth_auto_jsp = $lesys_file_path$+'/LesSystem/LesFileApi/oth_auto.jsp';  // 其他类型文件由浏览器自动处理(查看或下载)
var  _LesFile_ole_view_jsp = $lesys_file_path$+'/LesSystem/LesFileApi/iwo2009/ole_view.jsp';  // office类文件查看
var  _LesFile_ole_edit_jsp = $lesys_file_path$+'/LesSystem/LesFileApi/iwo2009/ole_edit.jsp';  // office类文件在线编辑

var _LesFile_ocx_file =  $lesys_file_path$+'/LesIEX.exe';

/*查看文件*/
function LesFile_View_File(FileID){
    //if(_LesFile_IsOleFile(FileID))
    //    ShowWin(_LesFile_ole_view_jsp + '?' + _BuildParams({'file_id': FileID}), 'lesfile_view_win_' + FileID);
    //else
    //    ShowWin(_LesFile_oth_auto_jsp + '?' + _BuildParams({'file_id': FileID}), 'lesfile_view_win_' + FileID);

    //判断是否ie浏览器，如不是ie浏览器，使用les-http打开查看页面
    if(_LesFile_IsOleFile(FileID)){
        if(isIE()){
            if(TryLESAX()){//直接打开页面
                ShowWin(_LesFile_ole_view_jsp + '?' + _BuildParams({'file_id': FileID}), 'lesfile_view_win_' + FileID);
            }else{
                alert("请安装在线查看控件");
                ShowWin(_LesFile_ocx_file, '',  600, 400);
            }
        }else{
            //保留个设置，谁安装了
            //ShowWin(_LesFile_ocx_file, '',  600, 400);
            window.location.href = "lesiex-"+_LesFile_ole_view_jsp + '?' + _BuildParams({'file_id': FileID});
        }
    }else{
        ShowWin(_LesFile_oth_auto_jsp + '?' + _BuildParams({'file_id': FileID}), 'lesfile_view_win_' + FileID);
    }
}

/*编辑文件*/
function LesFile_Edit_File(FileID, UserInfo){
    var pm = {'file_id':FileID, 'user_in':UserInfo};
    //判断是否ie浏览器，如不是ie浏览器，使用les-http打开查看页面
    if(_LesFile_IsOleFile(FileID)){
        if(isIE()){
            if(TryLESAX()){//直接打开页面
                ShowWin(_LesFile_ole_edit_jsp + '?' + _BuildParams({'file_id': FileID,'user_in':UserInfo}), 'lesfile_view_win_' + FileID);
            }else{
                alert("请安装在线查看控件");
                ShowWin(_LesFile_ocx_file, '',  600, 400);
            }
        }else{
            //保留个设置，谁安装了
            //ShowWin(_LesFile_ocx_file, '',  600, 400);
            window.location.href = "lesiex-"+_LesFile_ole_edit_jsp + '?' + _BuildParams({'file_id': FileID,'user_in':UserInfo});
        }
    }else{
        alert('可在线编辑文件类型必须是 Office 或 WPS 文档: \n\n' + _LESFILE_OLEDOC_EXTS);
        return;
    }

}

function _LesFile_GetFileInfo(FileID){
    var r = {};
    $.ajax({
        url: _les_file_rpc_api+'?'+_BuildParams({'cmd':'getinfo','file_id':FileID}),
        type: 'post',
        cache: false,
        async: false,
        contentType : 'application/json; charset=utf-8',
        success: function (text) {
            r = text;
        }
    });

    if(r.charAt(0)!='{') _throw_rpc_error('_LesFile_GetFileInfo', r);
    var r = JSON.parse(r); // {'FILE_ID', 'FILE_NAME', 'FILE_URL', 'FILE_SIZE', 'FILE_TIMESTAMP'}
    //var cmdv = 'download.clearly';   // 集成鸿翼文件加密系统: 强制下载非加密文件(下载到客户端的文件总是未加密文件)
    //var cmdv = 'download.security';  // 集成鸿翼文件加密系统: 强制下载加密文件  (下载到客户端的文件总是已加密文件)
    var cmdv = 'download';           // 直接下载文件, 可能是无加密的，也可能是加密的 (无鸿翼系统集成时使用)
    r['FILE_URL'] = _les_file_rpc_api +'?'+ _BuildParams({'cmd': cmdv, 'file_id': FileID});
    return r;
}