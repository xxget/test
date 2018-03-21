/**
 * 页面加载过度js效果
 * 等待页面的css样式加载完毕，Html内容加载完毕，样式生成后再进行展示，避免一开始加载内容后，逐渐渲染样式造成的不良视觉效果，增强用户体验。
 * 使用的时候，只需要在Head中引入就可以 xxg
 */
// 获取浏览器页面可见高度和宽度
var _PageHeight = document.documentElement.clientHeight, _PageWidth = document.documentElement.clientWidth;

// 计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0, _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2
		: 0;

// 加载gi图片地址
var Loadimagerul = "";

// 在页面未加载完毕之前显示的loading Html自定义内容
var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:'
		+ _PageHeight
		+ 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: '
		+ _LoadingLeft
		+ 'px; top:'
		+ _LoadingTop
		+ 'px; width:100px;; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url('
		+ Loadimagerul
		+ ') no-repeat scroll 5px 12px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">正在加载中...</div></div>';

// 呈现loading效果
document.write(_LoadingHtml);

// 监听加载状态改变
document.onreadystatechange = completeLoading;

// 加载状态为complete时移除loading效果
function completeLoading() {
	if (document.readyState == "complete") {
		var loadingMask = document.getElementById('loadingDiv');
		loadingMask.parentNode.removeChild(loadingMask);
	}
}