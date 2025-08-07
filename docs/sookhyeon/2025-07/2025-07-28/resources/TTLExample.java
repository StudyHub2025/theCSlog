public class TTLExample {

    public static void main(String[] args) {
        int ttl = 5;
        while (ttl > 0) {
            System.out.println("라우터 통과 TTL : " + ttl);
            ttl--; // 라우터 1개를 통과했으므로 TTL 감소
        }
        System.out.println("TTL 만료 - 패킷 폐기"); // TTL이 0이 되면 폐기
    }

}

/*
실행 결과
라우터 통과 TTL : 5
라우터 통과 TTL : 4
라우터 통과 TTL : 3
라우터 통과 TTL : 2
라우터 통과 TTL : 1
TTL 만료 - 패킷 폐기


 */