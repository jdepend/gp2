# gp2-΢����

- 1������eclipse\jdk8\maven\springboot\springcloud������
- 2����Ӧ�û���Ϊplatform��domain(core��target-execute��target-base)��application(core\target-execute-audit\target-execute-audit-base)��listener��portal(core\portal2\portal3)��mobile��rest��test 14�����̣�
- 3�����ṹ��com.rofine.gp��ʼ��
- 4��view����springboot�Ƽ���thymeleaf������
- 5���־û�����springdata��
- 6���������ɲ���uuid��
- 7�����Ķ����ϵ����manytoone��onetomany����������lazyload���ԣ���
- 8�����ݿ����mysql��
- 9������Repositoryģʽ��ͨ����IdEntity�е���ƽ̨�е�ApplicationContextUtil��ȡRepo��
- 10�����ÿɲ����쳣������ҵ���쳣���������쳣��������ҵ���쳣��
- 11��ͨ��ʵ���ϵ��ȡʵ�彫����ͨ��Repo����ȡ��ֻͨ��ʵ���ϵ����û�ȡ��
- 12����ͨ������������ȡʵ��ʱʹ��Repo��
- 13�����˴���ʵ���⣬��������������DomainService��ͨ��Repo.findʵ����ʵ�壻
- 14�������߼�����������ʵ���ϣ������DomainService��
- 15���������߼�������DomainService��Entity������
- 16�������¼���DomainService�׳���
- 17��ͨ��@Async��ʵ���첽������
- 18������ģ���д��������ӦΪ������������n����
- 19������ģ�����䱸n�������ĵ�Ԫ���Դ��룻
- 20����ָ��ִ�в��Ϊ΢����
- 21���������ĵ��ýӿڻ������ҽ�����Ҳʹ�øýӿڣ�
- 22����ObjectTarget��ObjectTargetExecute�䲻����ʹ��onetomany������

- ����˵����

- 1����mysql�´���gp2��gp2-target-execute�������ݿ⣻

- 2����������RegistrationServer��ObjectTargetExecuteServer����ObjectTargetExecuteAuditServer����WebServer��

- 3��ִ��insert-data.sql�������û���

- 4����������½��admin/123456��
