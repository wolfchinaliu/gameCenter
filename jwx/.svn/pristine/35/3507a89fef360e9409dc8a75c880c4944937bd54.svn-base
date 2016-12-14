/**
 * 摇一摇
 * @author minghua
 * @description 依赖zepto, fullAvatarEditor
 * @date 20150218
 */

var yaoApp = function () {

    var SHAKE_THRESHOLD = 600;
    var x, y, z;
    var last_x = 0, last_y = 0, last_z = 0;
    var last_update = 0;

    // 运动事件监听
    if (window.DeviceMotionEvent) {
        window.addEventListener('devicemotion', deviceMotionHandler, false);
    }
    else {
        alert('你的手机太差了，玩不了哇~~');
    }

    // 获取加速度信息
    function deviceMotionHandler(eventData) {

        var acceleration = eventData.accelerationIncludingGravity;
        var curTime = new Date().getTime();

        if ((curTime - last_update) > 100) {

            var diffTime = curTime - last_update;
            last_update = curTime;
            x = acceleration.x;
            y = acceleration.y;
            z = acceleration.z;
            var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 1000;
            
            if (speed > SHAKE_THRESHOLD) {

                doResult();

            }

            last_x = x;
            last_y = y;
            last_z = z;

        }

    }

    function doResult() {

        // 在这里执行摇一摇的代码
        alert("摇一摇成功！");

    }

}
yaoApp();
