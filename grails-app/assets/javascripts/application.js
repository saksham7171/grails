// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self

$(document).ready(function () {
    $('.dropdown-toggle').click(function () {
        $(this).parent().toggleClass('open')
    })

    $('.seriousness').change(function () {
        $.ajax({
            url: "/subscription/update",
            data: {id: $(this).attr('topicId'), seriousness: $(this).val()},
            success: function (jsonObj) {
                if (jsonObj) {
                    $('.json').css({"display": "block"});
                    if (jsonObj.message) {
                        $('.json').text(jsonObj.message);
                        $('.json').addClass('alert alert-success alert-dismissable');
                    }
                    else if (jsonObj.error) {
                        $('.json').text(jsonObj.error);
                        $('.json').addClass('alert alert-danger alert-dismissable');
                    }

                }
            }

        })
    })

    $('.visibility').change(function () {
        $.ajax({
            url: "/topic/save",
            data: {topicName: $(this).attr('topicName'), visibility: $(this).val()},
            success: function (jsonObj) {
                $('.json').css({"display": "block"});
                if (jsonObj.message) {
                    $('.json').text(jsonObj.message);
                    $('.json').addClass('alert alert-success alert-dismissable');
                }
                else if (jsonObj.error) {
                    $('.json').text(jsonObj.error);
                    $('.json').addClass('alert alert-danger alert-dismissable');
                }
            }
        })
    })

    $('#createTopicBtn').click(function (e) {
        e.preventDefault()
        $.ajax({
            url: "/topic/save",
            data: {topicName: $('#topicName').val(), visibility: $('#visibility').val()},
            success: function (jsonObj) {
                location.reload()
            }
        })
    })

    jQuery.validator.addMethod("passwordCheck", function (value) {
        var result = false;
        var password = $('#password').val();
        if (password === value) {
            result = true;
        }
        return result;
    }, "Password Fields Do Not Match");

    $(function () {
        $('#registration').validate({
            rules: {
                'firstName': {
                    required: true
                },
                'lastName': {
                    required: true
                },
                'password': {
                    required: true,
                    minlength: 5
                },
                'confirmPassword': {
                    required: true,
                    minlength: 5,
                    passwordCheck: true
                },
                'email': {
                    required: true,
                    remote: "/login/validateEmail"
                },
                'username': {
                    required: true,
                    remote: "/login/validateUserName"
                }
            },
            messages: {
                'firstName': {
                    required: "First Name Field Cannot Be Blank"
                },
                'lastName': {
                    required: "First Name Field Cannot Be Blank"
                },
                'password': {
                    required: "Password Field Cannot Be Blank",
                    minlength: "Minimum Password Size Is Of 5 Characters"
                },
                'confirmPassword': {
                    required: "Confirm Password Field Cannot Be Blank",
                    minlength: "Minimum Password Size Is Of 5 Characters"
                },
                'email': {
                    required: "E-Mail Address Field Cannot Be Blank",
                    remote: "Email address entered is already used"
                },
                'username': {
                    required: "User Name Field Cannot Be Blank",
                    remote: "Username entered is already used"
                }
            }
        });
    });

    $(function () {
        $('#login').validate({
            rules: {
                'password': {
                    required: true,
                    minlength: 5
                },
                'username': {
                    required: true,
                    remote: "/login/existUserName"
                }
            },
            messages: {
                'password': {
                    required: "Password Field Cannot Be Blank",
                    minlength: "Minimum Password Size Is Of 5 Characters"
                },
                'username': {
                    required: "User Name Field Cannot Be Blank",
                    remote: "Username entered doesn't exist"
                }
            }
        });
    });

    $('.readingItem').click(function (e) {
        e.preventDefault();
        var self = $(this);
        $.ajax({
            url: "/readingItem/changeIsRead",
            data: {resourceId: $(this).attr('resourceId')},
            success: function (jsonObject) {
                if (jsonObject) {
                    if (jsonObject.message) {
                        $('.json').text(jsonObject.message)
                        $('.json').addClass('alert alert-success')
                        if (jsonObject.status == true) {

                            self.text("Mark as Unread")
                        }
                        else {
                            self.text("Mark as Read")
                        }
                    }
                    else if (jsonObject.error) {
                        $('.json').text(jsonObject.error)
                        $('.json').addClass('alert alert-danger')


                    }
                }
            }
        })
    });

})