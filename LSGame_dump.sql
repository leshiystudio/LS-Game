PGDMP     "                    z            LSGame    15.1 (Debian 15.1-1.pgdg110+1)    15.0     .           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            /           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            0           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            1           1262    16384    LSGame    DATABASE     s   CREATE DATABASE "LSGame" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE "LSGame";
                postgres    false            $          0    16385    authorities 
   TABLE DATA           =   COPY public.authorities (id, user_id, authority) FROM stdin;
    public          postgres    false    214   
       &          0    16390 
   characters 
   TABLE DATA           x   COPY public.characters (id, name, health, armor, dexterity, speed, power, count_win, count_losing, user_id) FROM stdin;
    public          postgres    false    216   {
       (          0    16402    fight_history 
   TABLE DATA           G   COPY public.fight_history (id, winner_id, losser_id, date) FROM stdin;
    public          postgres    false    218   ^       *          0    16407    users 
   TABLE DATA           H   COPY public.users (id, username, password, groups, enabled) FROM stdin;
    public          postgres    false    220   >       7           0    0    authorities_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.authorities_id_seq', 17, true);
          public          postgres    false    215            8           0    0    characters_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.characters_id_seq', 14, true);
          public          postgres    false    217            9           0    0    fight_history_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.fight_history_id_seq', 15, true);
          public          postgres    false    219            :           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 29, true);
          public          postgres    false    221            $   Y   x�M�;
�0D�z�b$oMR
��@�Np����<��:���w^��0����-ë��Z+�B �F5����N������Π�=��}�|/�      &   �   x�=��j�0�ϳS�k�X�C鵗�1m�b9���3RL,��vv���ZEN�����%�bLT$5'ߵ]�{�oeY�g�N͢�9 �k��E��̹/4����T�`��ϒ��1/꘲}�S��A�$�Z���!l���Dq.���^$C�Gx���m��r�C�=\6ˌ�ߥ��t��9D=bj瓨��A����,�盈< c�?;      (   �   x�u��1�壘m ��2v-鿎�HQ�U$��d��8T�7���)�������R,5֜6����Q��f2��� �eEQ���)��WKsE�	f�[�0_<j�U�|�%�K�ev�}���i� ��m�D�!F�G�J�'��hƂФ�$��cFz���>��)���I�t+%t����x�=S�7�3̛��	��ޤ^9��D�  Zk      *   �   x�M���0�����rxƭh��[	Sb���V�I�/�d8*5�SE�D�^�Ӭ�e,DC�=�Q��<�j�V6c�=�o���搵����J
Ai5��na��1n�Qz.��P�	\x�(-u��o�n�渾�����׉�I����{"�C�     