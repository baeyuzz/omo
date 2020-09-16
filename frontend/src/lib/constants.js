export default {
    URL_TYPE: {},
    METHODS: {
        // 유저 관련 메소드
        DELETE_USER: 'deleteUser',
        UPDATE_USER: 'updateUser',
        LOGIN_USER: 'loginUser',
        LOGOUT_USER: 'logoutUser',
        CREATE_USER: 'createUser',
        GET_USER: 'getUser',
        ERROR: 'error',

        // 이메일 중복체크 메소드
        EMAILCHECK: 'emailCheck',

        // 비밀번호 재설정 메소드
        RESETMYPASSWORD: 'resetMyPassword',
        // 비밀번호 재설정 요청 메소드
        RESETMYPASSWORDREQ: 'resetMyPasswordReq',
    }
}