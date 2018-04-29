<?php

namespace MobileBundle\Controller;

use AppBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class UserController extends Controller
{
    public function findallAction()
    {

        $users = $this->getDoctrine()->getManager()
            ->getRepository('AnimauxBundle:Animaux')
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($users);
        return new JsonResponse($formatted);
    }

    public function  loginAction( $email,$password )
    {

        $em1 = $this->getDoctrine()->getManager();
        $user=$em1->getRepository('AppBundle:User')->findOneBy(array("email"=>$email));

        $test=array();
        $u=new User();


       if ($user==null)
        {
            $u->setId(0);
            $us=["user"=>$u];

            $msg=["message"=>"email non valid"];
            array_push($test,$msg);
            array_push($test,$us);

        }

        else if($user->getPassword() != $password)
        {
            $u->setId(0);
            $us=["user"=>$u];
            $msg=["message"=>"password non valid"];
            array_push($test,$msg);
            array_push($test,$us);
        }
        else
            {
                $u=$user;

                $us=["user"=>$u,"message"=>"connection etablie"];

                //array_push($test,$msg);
                array_push($test,$us);
        }

         $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($test);
        return new JsonResponse($formatted);
        
        }


        public function registerAction($username,$email,$password,$sexe,$telephone,$image,$ville)
        {

            $user=new User();
            $user->setUsername($username);
            $user->setUsernameCanonical($username);
            $user->setEmail($email);
            $user->setEmailCanonical($email);
            $user->setEnabled(1);
            $user->setSalt(null);


            $user->setPassword($password);
            $user->setRoles( array("ROLE_USER"));
            $user->setSexe($sexe);
            $user->setTelephone($telephone);
            $user->setImage($image);
            $user->setVille($ville);

            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($user);
            return new JsonResponse($formatted);



        }

}
