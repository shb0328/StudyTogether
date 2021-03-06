import java.io.*;
import java.util.*;
public class Main {
	public static int N,M,ans;
	public static int [][] board = new int[101][101];
	public static boolean [][] visited = new boolean[101][101];
	public static int [][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static class Position{
		int y;
		int x;
		Position(){}
		Position(int a,int b){
			y = a;
			x = b;
		}
	}
	
	public static void simul(){
		boolean suc = false;
		Queue<Position> q= new LinkedList<Position>();
		q.add(new Position(0,0));
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				visited[i][j] = false;
			}
		}
		visited[0][0] = true;
		int [][] count = new int[101][101];
		while(!q.isEmpty()){
			Position cur = q.poll();
			for(int i=0;i<4;i++){
				int ny = cur.y + dir[i][0];
				int nx = cur.x + dir[i][1];
				if (ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx]) continue;			
				if(board[ny][nx]==1){ //외부에서 노출된 치즈 발견!
					count[ny][nx]++;
				}
				else{
					visited[ny][nx] =true;
					q.add(new Position(ny,nx));
				}
			}
		}
		
		//count배열에서 2 이상인 치즈 녹이기
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(count[i][j]>=2){
					suc = true;
					board[i][j] = 0;
				}
			}
		}
		if(suc==true) {
			ans++;
			simul();
		}
	}
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++){
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		simul();
		System.out.println(ans);
	}

}
