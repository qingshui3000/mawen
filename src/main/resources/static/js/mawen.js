function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success:function (response) {
            if(response.code == 200){
                $("#comment_section").hide();
                $("#comment_each");
            }else if(response.code == 2000) {
                var isAccepted = confirm(response.message);
                if(isAccepted){
                    window.open("https://github.com/login/oauth/authorize?client_id=353ff89757379c74fb8d&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                    window.localStorage.setItem("closable",true);
                    window.location.reload();
                }
            }else{
                alert(response.message);
            }
            console.log(response);
        },
        dataType:"json"
    });
}