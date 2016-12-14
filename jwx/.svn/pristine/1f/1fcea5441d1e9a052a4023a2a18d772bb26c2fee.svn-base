/**
 * 上传头像
 * @author xieliang
 * @description 依赖swfobject.js, fullAvatarEditor
 * @date 20140714
 */
define(function (require) {
    "use strict";

    var User = require("../lib/user"),
        Tools = require("../lib/tools"),
        $ = require("../lib/jquery"),
        Dialog = require("../lib/dialog");


    var swf;

    User.init();


    var success = function (res) {
        if (res.code == 5) { //上传
            if (res.content.error === 0) {
                Dialog.success("上传成功");
                setTimeout(function () {
                    location.reload(true);
                }, 1e3);
                // swf && swf.call("changePanel", "upload");
                // $("#j-preview img").each(function(index) {
                //     this.src = res.content.data[index] + '?r=' + (+new Date);
                // });
                // window.scrollTo(0, $("#j-h1").offset().top);
            } else if (res.content.error === 1002) {
                User.exit().login(function () {
                    location.reload(true);
                });
            } else {
                Dialog.error(res.content.msg || '上传失败');
            }
        }
    }

    swfobject.addDomLoadEvent(function () {
        swfobject.embedSWF(
            '../../../res/fullAvatarEditor/fullAvatarEditor.swf', //flash文件的路径
            'swf', //替换容器
            1020, //宽
            450, //高
            '10.1.0', //版本
            '../../../res/fullAvatarEditor/expressInstall.swf', //安装swf
            {
                tab_visible: false,
                upload_url: '/api/users/upload-avatars',
                avatar_sizes: "265*265|150*150|100*100|60*60|40*40",
                avatar_sizes_desc: "265像素*265像素|150*150像素|100*100像素|60*60像素|40*40像素",
                isShowUploadResultIcon: false,
                avatar_tools_visible: false,
                id: 'swf_xl'
            }, //参数
            {
                menu: 'true',
                scale: 'noScale',
                allowFullscreen: 'true',
                allowScriptAccess: 'always',
                wmode: 'transparent'
            }, {
                id: 'swf_xl',
                name: 'swf_xl'
            }, //属性
            function (e) {
                swf = e.ref;
                swf.eventHandler = function (json) {
                    success.call(swf, json);
                }
            }
        );
    });

});