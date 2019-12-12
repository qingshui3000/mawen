//提交回复
function comment1() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2Target(questionId,1,content);
}

function comment2(e) {
    var parentId = e.getAttribute("data-id");
    var content = $("#input-" + parentId).val();
    comment2Target(parentId,2,content);
}

function comment2Target(targetId,type,content) {
    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success:function (response) {
            if(response.code == 200){
                window.location.reload();
            }else if(response.code == 2000) {
                var isAccepted = confirm(response.message);
                if(isAccepted){
                    window.open("https://github.com/login/oauth/authorize?client_id=353ff89757379c74fb8d&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                    window.localStorage.setItem("closable",true);
                }
            }else{
                alert(response.message);
            }
            console.log(response);
        },
        dataType:"json"
    });
}
//展开二级评论
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var subComment = $("#subComment-"+id);
    if(subComment.hasClass("in")){
        //折叠
        subComment.removeClass("in");
        e.classList.remove("active");
    }else{
        var subCommentContainer = $("#subComment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            subComment.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        }else{
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, subComment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": subComment.user.userfaceUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": subComment.user.name
                    })).append($("<div/>", {
                        "html": subComment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(subComment.gmtCreate).format('YYYY-MM-DD HH:mm:ss')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                subComment.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }


}

function showSelectTags() {
    $('#selectTags').show();
}

function selectTags(e) {
    //获取输入框值
    var value = e.getAttribute("data-tag");
    var previous = $('#tag').val();
    if(previous.indexOf(value) == -1){
        if(previous){
            $('#tag').val(previous+','+value);
        }else {
            $('#tag').val(value);
        }
    }
}