$(document).ready(function() {
    // if(navigator.appName!="Microsoft Internet Explorer")
    // return;
    var trim_Version = getBrowserVersion();
    if (trim_Version == "MSIE5.0" || trim_Version == "MSIE6.0" || trim_Version == "MSIE7.0") {
        window.top.location = "/needUpdateBrowser.html";
        return;
    }
});
$.ajaxSetup({'cache':false});

var getBrowserVersion = function() {
    var b_version = navigator.appVersion;
    var version = b_version.split(";");
    if (version.length < 2)
        return version[0];
    return version[1].replace(/[ ]/g, "");
};

var commonUtils = {
    // 表单序列化数据转换成json数据
    formToJson : function(formId) {
        var serializeObj = {};
        var serializeArray = $('#' + formId).serializeArray();
        $(serializeArray).each(function() {
            serializeObj[this.name] = this.value;
        });
        return serializeObj;
    },
    // 表单json赋值
    formSetValue : function(data) {
        for ( var i in data) {
            var ele = $("form [name='" + i + "']");
            if ($(ele).is('input')) {
                var type = $(ele).attr('type');
                if (type == 'radio') {
                    $("input[name='" + i + "'][value='" + data[i] + "']").attr("checked", true);
                } else {
                    $(ele).val(data[i]);
                }
            }

        }
    }
};

var pagingInfo = function(data) {
    return '<div data-bind="visible:' + data + '().length >0"><div class="dataTables_info" data-bind="text:' + data + '.pagingInfo()"></div>' + '<div class="dataTables_paginate paging_simple_numbers">' + '<ul class="pagination">' + '<li data-bind="css: { disabled: ' + data + '.isFirstPage() }" class="paginate_button previous disabled">' + '<a href="#" data-bind="click: ' + data + '.toFirstPage">&lt;&lt;</a>' + '</li>' + '<li data-bind="css: { disabled: !' + data
            + '.hasPreviousPage() }" class="paginate_button previous disabled">' + '<a href="#" data-bind="click: ' + data + '.toPreviousPage">&lt;</a>' + '</li>' + '<!-- ko foreach: ' + data + '.pages -->' + '<li data-bind="css: { active: $data == ' + data + '.pageNumber() }" class="paginate_button">' + '<a href="#" data-bind="text: $data, click: ' + data + '.pageNumber.bind($data)"></a>' + '</li>' + '<!-- /ko -->' + '<li data-bind="css: { disabled: !' + data
            + '.hasNextPage() }" class="paginate_button next disabled">' + '<a href="#" data-bind="click: ' + data + '.toNextPage">&gt;</a>' + '</li>' + '<li data-bind="css: { disabled: ' + data + '.isLastPage() }" class="paginate_button next disabled">' + '<a href="#" data-bind="click: ' + data + '.toLastPage">&gt;&gt;</a>' + '</li>' + '</ul>' + '</div></div>';
};

var emptyForm = function(formId) {
    var ipts = $("#" + formId + " input");
    for (var i = 0; i < ipts.length; i++) {
        var type = $(ipts[i]).prop('type');
        if (type == 'checkbox') {
            $(ipts[i]).prop("checked", false);
        } else {
            $(ipts[i]).val('');
        }
    }
};

var ie8PlaceHolder = function() {
    if (!('placeholder' in document.createElement('input'))) {
        $('input[type="text"][placeholder], textarea[placeholder]').each(function() {
            var o = $(this);
            if (o.attr('placeholder') != '') {
                o.addClass('IePlaceHolder');
                if ($.trim(o.val()) == '') {
                    o.val(o.attr('placeholder')).css('color', '#888888');
                    o.addClass('IeIsEmpty');
                    o.removeClass('focusedon');
                }
            }
        });

        $('.IePlaceHolder').focus(function() {
            var o = $(this);
            if (o.val() == o.attr('placeholder')) {
                o.css('color', '#666666');
                o.addClass('focusedon');
            } /* endIF */
        });

        $('.IePlaceHolder').blur(function() {
            var o = $(this);
            if ($.trim(o.val()) == '' || $.trim(o.val()) == o.attr('placeholder')) {
                o.val(o.attr('placeholder'));
                o.css('color', '#888888');
                if (!o.hasClass('IeIsEmpty'))
                    o.addClass('IeIsEmpty');
                o.removeClass('focusedon');
            }
        });

        $('.IePlaceHolder').on("keyup change paste", function() {
            var o = $(this);
            if ($.trim(o.val()) != '' && $.trim(o.val()) != o.attr('placeholder')) {
                o.css('color', '#111111');
                o.removeClass('IeIsEmpty');
            }
        }).on('keydown', function() {
            var o = $(this);
            if ($.trim(o.val()) != '' && $.trim(o.val()) == o.attr('placeholder')) {
                o.val('');
            }
        }).on("click", function() {
            var o = $(this);
            if (o.val() != o.attr('placeholder'))
                return;
            if (this.setSelectionRange) {
                this.focus();
                this.setSelectionRange(0, 0);
            } else if (this.createTextRange) {
                var r = this.createTextRange();
                r.collapse(true);
                r.moveEnd('character', 0);
                r.moveStart('character', 0);
                r.select();
            }
        });
    }
};

if (!Array.isArray) {
    Array.isArray = function(arg) {
        return Object.prototype.toString.call(arg) === '[object Array]';
    };
};

if (!Array.prototype.find) {
    Array.prototype.find = function(predicate) {
        if (this == null) {
            throw new TypeError('Array.prototype.find called on null or undefined');
        }
        if (typeof predicate !== 'function') {
            throw new TypeError('predicate must be a function');
        }
        var list = Object(this);
        var length = list.length >>> 0;
        var thisArg = arguments[1];
        var value;

        for (var i = 0; i < length; i++) {
            value = list[i];
            if (predicate.call(thisArg, value, i, list)) {
                return value;
            }
        }
        return undefined;
    };
};

if (!Array.prototype.filter) {
    Array.prototype.filter = function(fun/* , thisArg */) {
        'use strict';

        if (this === void 0 || this === null) {
            throw new TypeError();
        }

        var t = Object(this);
        var len = t.length >>> 0;
        if (typeof fun !== 'function') {
            throw new TypeError();
        }

        var res = [];
        var thisArg = arguments.length >= 2 ? arguments[1] : void 0;
        for (var i = 0; i < len; i++) {
            if (i in t) {
                var val = t[i];

                if (fun.call(thisArg, val, i, t)) {
                    res.push(val);
                }
            }
        }

        return res;
    };
}

// 时间置空
function resetValue(obj) {
    $(obj).val("").change();
}

// x：1登录，2个人中心，空其它
var setLoginAnchor = function(x) {
    $.ajax({
        url : "/api/system/user/self/name",
        method : "GET",
        success : function(d) {
            if ($('#usernameSpan').text() == d) {
                if (x == 1) {
                    changeCaptcha(1);
                    changeCaptcha(2);
                    $('#loginModal').modal('show');
                } else if (x == 2)
                    $('#statusDropdown').dropdown();
                return;
            }
            $('#usernameSpan').text(d);
            if (d == "") {
                $('#loginA').show();
                $('#welcomeA').hide();
            } else {
                $('#loginA').hide();
                $('#welcomeA').show();

            }
        },
        error : ajaxError,
        cache : false
    });
};

var ajaxError = function(jqXHR, textStatus, errorThrown) {
  var status = jqXHR.status;
  if (status == 401 || status == 302) {
    window.top.location.reload();
    return false;
  } else if (status == 409) {
    toastr.error("登录用户已变化，将更新为新用户对应数据");
    setTimeout("window.location.reload();", 2000);
  } else if (status == 502 || status == 504){
    toastr.error("请求超时，将自动刷新页面");
    setTimeout("window.location.reload();", 3001);
  } else {
    if(jqXHR.responseText.indexOf("<pre>")!=-1){
      toastr.error(JSON.parse(jQuery(jqXHR.responseText).text()).message);
    } else{
      toastr.error(JSON.parse(jqXHR.responseText).message);
    }

  }
};

var ajaxErrorNotShow = function(jqXHR, textStatus, errorThrown) {
    var status = jqXHR.status;
    if (status == 401 || status == 302) {
        setLoginAnchor(1);
    } else if (status == 409) {
        toastr.error("登录用户已变化，将更新为新用户对应数据");
        setTimeout("window.location.reload();", 2000);
    } else if (status == 502 || status == 504){
        toastr.error("请求超时，将自动刷新页面");
        setTimeout("window.location.reload();", 3001);
    } else if (status != 404) {
        toastr.error(JSON.parse(jqXHR.responseText).message);
    }
};

/**
 * 20160823 lhm 获取查询字符串
 */
var queryStrings = function() {
    var params = document.location.search, reg = /(?:^\?|&)(.*?)=(.*?)(?=&|$)/g, temp, args = {};
    while ((temp = reg.exec(params)) != null)
        args[temp[1]] = decodeURIComponent(temp[2]);
    return args;
};

var queryString = function(key) {
    return (document.location.search.match(new RegExp("(?:^\\?|&)" + key + "=(.*?)(?=&|$)")) || [ '', null ])[1];
}

/*
 * 返回当前时间yyyy-MM-dd
 */
var getFormatDate = function() {
    var d = new Date();
    var s = d.getFullYear();
    s += "-" + (d.getMonth() + 1 > 9 ? (d.getMonth() + 1) : "0" + (d.getMonth() + 1));
    s += "-" + (d.getDate() > 9 ? d.getDate() : "0" + d.getDate());
    return s;
}

// added by wanglei
var getFormatCHNDate = function () {
  var d = new Date();
  var s = d.getFullYear();
  s += "年" + (d.getMonth() + 1 > 9 ? (d.getMonth() + 1) : "0" + (d.getMonth() + 1));
  s += "月" + (d.getDate() > 9 ? d.getDate() : "0" + d.getDate());
  s += "日";
  return s;
}

//add by wanglei
var getCurrentYear = function(){
  var d = new Date();
  return d.getFullYear();
}

/*
 * 返回当前时间yyyy-MM-dd hh:mm:ss
 */
var getFormatDateTime = function() {
    var d = new Date();
    var s = d.getFullYear();
    s += "-" + (d.getMonth() + 1 > 9 ? (d.getMonth() + 1) : "0" + (d.getMonth() + 1));
    s += "-" + (d.getDate() > 9 ? d.getDate() : "0" + d.getDate());
    s += " " + (d.getHours() > 9 ? d.getHours() : "0" + d.getHours());
    s += ":" + (d.getMinutes() > 9 ? d.getMinutes() : "0" + d.getMinutes());
    s += ":" + (d.getSeconds() > 9 ? d.getSeconds() : "0" + d.getSeconds());
    return s;
}

$.ajaxSetup({cache:false});
function closeWindow(){
  if (navigator.userAgent.indexOf("MSIE") > 0) {
    if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {    //适用于ie6不带提示关闭窗口
      window.opener = null;
      window.close();
    } else {
      window.open('', '_top');         //ie7之后不提示关闭窗口
      window.top.close();
    }
  }
  else if (navigator.userAgent.indexOf("Firefox") > 0) {        //Firefox不提示关闭窗口
    window.location.href = 'about:blank ';
  } else {
    window.opener = null;
    window.open('', '_self', '');        //其他浏览器
    window.close();
  }
}
