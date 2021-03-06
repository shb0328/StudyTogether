#include <string>
#include <vector>
#include <queue>
#include <iostream>
using namespace std;

vector<string> solution(vector<string> s) {
    vector<string> answer;
    for(string str: s){
        deque<char> dq;
        int cnt=0,idx=0,len = str.size();
        //110개수 세기, 제거한 값 dq에 넣기
        for(int i=0;i<len;i++){
            char c = str[i];
            dq.push_back(c);
            idx++;
            if(idx>2){
                if(dq[idx-3]=='1' && dq[idx-2]=='1' && dq[idx-1]=='0'){     //110
                    dq.pop_back();
                    dq.pop_back();
                    dq.pop_back();
                    cnt++;
                    idx-=3;
                }
            }
        }
        string result="";
        int lastZero = -1;
        for(int i=idx-1;i>=0;i--){
            if(dq[i]=='0'){
                lastZero=i;
                break;
            }
        }
        
        for(int i=0;i<=lastZero;i++)
            result+=dq[i];
        while(cnt--)
            result+="110";
        for(int i=lastZero+1;i<idx;i++)
            result+=dq[i];
        answer.push_back(result);
    }
    return answer;
}
