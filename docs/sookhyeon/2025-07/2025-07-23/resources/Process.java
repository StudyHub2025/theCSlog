class Process {
    String id;           // 프로세스 ID (예: "P1")
    int arrivalTime;     // 도착 시간
    int burstTime;       // 전체 수행 시간
    int remainingTime;   // 남은 시간 (SRT, RR, MLFQ 등에서 사용)
    int priority;        // 우선순위 (작을수록 높은 우선순위)

    public Process(String id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime; // 초기에는 전체 실행 시간과 동일
        this.priority = priority;
    }
}
