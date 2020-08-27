let errorSpan = {
    "font-size": "12px",
    "color": "red"
};

let span = {
    "font-size": "12px",
    "color": "beige"
};
let flag = false;
let type = 'post';
let async = 'false';
let error = '';
let userId = $('#userId').val();
let userName = $('#userName');
let userEmail = $('#userEmail');
let password = $('#password');
let userLevel = $('#userLevel');
let updateRes = $('#updateRes');
let userState = $('input:radio:checked').val();

/*userName.blur(function () {
    let url = '/user/findByUserName';
    let data = {'userName': userName.val()};
    sendAjax(url, type, async, data, success, error);
});
userEmail.blur(function () {
    let url = '/user/findByUserEmail';
    let data = {'userEmail': userEmail.val()};
    sendAjax(url, type, async, data, success, error);
});
*/
function editUser() {
    let username = userName.val().trim();
    let email = userEmail.val().trim();
    let pwd = password.val().trim();
    let level = userLevel.val().trim();
    let url = '/user/update';
    if (userId.length > 0
        && username.length > 0
        && email.length > 0
        && pwd.length > 0
        && level.length > 0
        && userState.length > 0
    ) {
        let data = {
            userId: userId,
            userName: username,
            password: pwd,
            userEmail: email,
            userState: userState,
            userLevel: level
        };
        console.log(data);
        sendAjax(url, type, async, data, success, error);
    } else {
        updateRes.css(errorSpan);
        updateRes.html("参数不能为空！");
    }
}

function success(res) {
    if (res.code !== 0) {
        updateRes.css(errorSpan);
        let str = '<p>' + res.msg + '</p>';
        let row = res.data;
        if (row) {
            for (let i in row)
                str += '<p>' + row[i] + '</p>';
        }
        updateRes.html(str);
    } else {
        updateRes.html('');
    }
    if (flag) {
        window.location.href = '/user/index/1';
    }
}

/**
 * 封装jQuery的发送ajax请求
 * @param url   请求地址
 * @param type  请求类型
 * @param async 是否异步请求
 * @param data  请求参数
 * @param success  请求成功调用方法
 * @param error     请求失败调用方法
 * @returns {boolean} //是否成功
 */
function sendAjax(url, type, async, data, success, error) {
    $.ajax({
        url: url,//请求路径
        data: data,
        async: async,
        type: type,//GET
        dataType: "JSON",//需要返回JSON对象(如果Ajax返回的是手动拼接的JSON字符串,需要Key,Value都有引号)
        success: function (res) {
            flag = res.code === 0;
            console.log("res=" + flag);
            success(res);
        },
        error: function (a, b, c) {
            console.log("请求出错！");
            //error();
            flag = false;
        }
    });
}