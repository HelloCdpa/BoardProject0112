###엔티티 설계
1. MemberEntity
2. id(long), email, password, name
3. 회원 : 게시글 = 1: N관계
4. 회원 : 댓글 = 1:N관계
5. 게시글 : 댓글 1: N관계
### MemberEntity를 추가하고 게시글, 댓글과의 연관관계 매핑을 구현해 보세요
1. 회원이 게시글을 작성하는 기능까지 만들어 봅시다.