create room:
- 1 room min.6 orang
- pembuat room jadi room master
- yg join, bisa ready
- game dimulai saat isi room udah >=6 orang dan semua yg join udah kluarin status ready

game start:
- system tentuin random siapa werewolf dan siapa penduduk desa, werewolf min.2, penduduk desa min.4,
  (dari wiki, werewolf disaranin 1/3 pemain, dilakukan pembulatan terdekat)
- dimulai dari 1st night, para werewolf berkenalan dan tau satu sama lain, penduduk desa tidur
- 1st day, penduduk desa dikasi tau ada brp werewolf

game repeat:
- mulai dari 2nd night, werewolf berdiskusi buat nentuin siapa penduduk desa yg dibunuh
- vote leader ditentuin system bergiliran, leader ngitung jumlah vote, klo werewolf sisa 1 ya udah gk perlu vote2an
  (choice by highest vote, klo gagal bisa vote ulang sampai ada yg highest vote, sistem paxos sama kea day)
- pada day selanjutnya, korban yg divote werewolf kemarinnya mati
- yg masih hidup vote buat nentuin siapa yg dicurigai werewolf
- vote leader ditentuin system bergiliran, leader ngitung jumlah vote
- hasil vote diterusin ke acceptor (gk boleh leader)
- choice by highest vote, klo gagal ulang lagi 1x, klo masih sama gk ada yg dibunuh

consensus paxos:
- tentuin leader (by system)
- semua player ngevote 1 target, kirimkan ke leader
- leader nentuin highest vote, klo gk ada, repeat vote (vote gagal)
- highest vote disetujuin sama selain leader, minimal 1/2n+1
- klo diaccept, execute, klo direject, vote gagal

end phase:
- werewolf >= penduduk desa, werewolf menang
- werewolf abis, penduduk desa menang

misc:
- yg mati cuman bisa liat status game (= spectator)
- semua yg mati ditampilin pada semua client, beserta rolenya
- klo ada yg kurang tambahin aja
- klo ada yg salah benerin aja

How to run:
Server
java -jar Werewolf_Server.jar [ip_adress_server] [Port] [Num_Player]
Client
java -jar Werewolf_Client.jar [ip_adress_server] [Port]
