let baseURL = '',
    mediaURL = '',
    isDev = true; // 是否是测试环境
if(isDev) {
    baseURL = 'http://comic.3gi.cc';
	// mediaURL = 'http://img.9icph.cn';
	mediaURL = 'http://img.ttnnr.com';
} else {
    baseURL = '';
}

export default {
    baseURL,
	mediaURL
}